package com.webCourses.app.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.Files;
import com.webCourses.app.dao.course.CourseDAO;
import com.webCourses.app.dao.group.GroupDAO;
import com.webCourses.app.dao.material.MaterialDAO;
import com.webCourses.app.dao.member.MemberDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.material.Material;
import com.webCourses.app.model.user.User;
import com.webCourses.app.utils.ApplicationUtils;

@Controller
@SessionAttributes({ "material" })
@RequestMapping("/material")
public class MaterialController {

	@Autowired
	private CourseDAO CourseDao;
	@Autowired
	private GroupDAO GroupDao;
	@Autowired
	private UserDAO UserDao;
	@Autowired
	private MemberDAO MemberDao;
	@Autowired
	private MaterialDAO MaterialDao;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { "/add", "/edit" }, method = RequestMethod.GET)
	public String add(Locale locale, @RequestParam(value = "courseId", required = false) Long courseId,
			@RequestParam(value = "id", required = false) Long materialId, ModelMap model) {
		Material material = null;
		Course course = null;
		if (materialId == null) { // add
			course = CourseDao.getCourseById(courseId);
			material = new Material();
			material.setCourse(course);
		} else { // edit
			material = MaterialDao.getById(materialId);
			course = material.getCourse();
		}

		// can user edit/add new material to course
		if (course.getUser().equals(getLoggedUser())) {
			model.addAttribute("material", material);
			if (materialId == null) {
				return "material/add";
			} else {
				return "material/edit";
			}
		} else {
			return "error";
		}
	}

	@RequestMapping(value = { "/add", "/edit" }, method = RequestMethod.POST)
	public String addSubmit(@ModelAttribute("material") Material material, BindingResult bindingResult,
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, Locale locale,
			ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "material/add";
		}
		try {
			if (!uploadFile.isEmpty()) {
				validateFile(uploadFile, locale);
				saveFile(material, uploadFile);
			}
		} catch (FileUploadException e) {
			bindingResult.reject("uploadFile", e.getMessage());
			return "material/add";
		}
		if (material.getMaterialId() == null) {
			material.setCreatedUser(this.getLoggedUser());
			List<Material> addedMaterials = MaterialDao.getAllMaterialsForCourse(material.getCourse());
			int order = 0;
			if (addedMaterials.size() > 0) {
				order = addedMaterials.get(addedMaterials.size() - 1).getOrderNumber();
				order++;
			}
			material.setOrderNumber(order);
			MaterialDao.add(material);
		} else {
			MaterialDao.update(material);
		}
		return "redirect:/material/list?id=" + material.getCourse().getCourseId();
	}

	private void saveFile(Material material, MultipartFile uploadFile) throws FileUploadException {
		try {
			String oryginalFileName = uploadFile.getOriginalFilename();
			String ext = Files.getFileExtension(oryginalFileName);
			String nameWithoutExtension = Files.getNameWithoutExtension(oryginalFileName);
			String fileName = ApplicationUtils.getMd5FromString(nameWithoutExtension + System.currentTimeMillis())
					+ "." + ext;
			File file = new File(ApplicationUtils.getRealPathForDirectory("resources") + "/materials/"
					+ material.getCourse().getCourseId() + "/" + fileName);
			FileUtils.writeByteArrayToFile(file, uploadFile.getBytes());
			material.setFile(fileName);
			material.setOrgFileName(oryginalFileName);
		} catch (IOException e) {
			throw new FileUploadException("Cannot save file", e);
		}
	}

	private List<String> getAcceptableExtension() {
		List<String> acceptableExtension = new ArrayList<String>();
		acceptableExtension.add("application/pdf");
		acceptableExtension.add("application/vnd.ms-powerpoint");
		return acceptableExtension;
	}

	private void validateFile(MultipartFile file, Locale locale) throws FileUploadException {
		if (!getAcceptableExtension().contains(file.getContentType())) {
			throw new FileUploadException(messageSource.getMessage("Material.fileUpload.error", null, locale));
		}
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public ModelAndView show(Locale locale, @RequestParam(value = "id") Long materialId) {
		Material material = MaterialDao.getById(materialId);
		ModelAndView model = new ModelAndView();
		if (ApplicationUtils.isCourseMember(GroupDao, material.getCourse(), getLoggedUser())
				|| getLoggedUser().equals(material.getCourse().getUser())) {
			String link = "materials/" + material.getCourse().getCourseId() + "/" + material.getFile();
			model.addObject("link", link);
			return new ModelAndView("redirect:https://docs.google.com/viewer?url="
					+ ApplicationUtils.urlPathWithResources + link);
		} else {
			return new ModelAndView("redirect:/course/join?id=" + material.getCourse().getCourseId());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Locale locale, @RequestParam(value = "id") Long courseId) {
		ModelAndView model = new ModelAndView();
		List<Material> materials;
		Course course = CourseDao.getCourseById(courseId);
		if (course.getUser().equals(getLoggedUser())) {
			materials = MaterialDao.getAllMaterialsForCourse(course);
			model.addObject("materials", materials);
			model.addObject("course", course);
		}
		model.setViewName("material/list");
		return model;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(Locale locale, @RequestParam(value = "id") Long materialId) {
		Material materialToRemove = MaterialDao.getById(materialId);
		String fileName = materialToRemove.getFile();
		File file = new File(ApplicationUtils.getRealPathForDirectory("resources") + "/materials/"
				+ materialToRemove.getCourse().getCourseId() + "/" + fileName);
		file.delete();
		Long courseId = materialToRemove.getCourse().getCourseId();
		MaterialDao.remove(materialToRemove);
		return new ModelAndView("redirect:/material/list?id=" + courseId);
	}

	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserDao.getUserByEmail(auth.getName());
	}
}
