package com.webCourses.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.webCourses.app.dao.course.CourseDAO;
import com.webCourses.app.dao.group.GroupDAO;
import com.webCourses.app.dao.member.MemberDAO;
import com.webCourses.app.dao.user.UserDAO;
import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.group.Group;
import com.webCourses.app.model.member.Member;
import com.webCourses.app.model.user.User;

@Controller
@SessionAttributes({ "group", "member" })
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private CourseDAO CourseDao;
	@Autowired
	private GroupDAO GroupDao;
	@Autowired
	private UserDAO UserDao;
	@Autowired
	private MemberDAO MemberDao;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Locale locale, @RequestParam(value = "id", required = false) Long groupId, ModelMap model) {
		Group groupToDisplay;
		
		if (groupId == null) {
			groupToDisplay = new Group(); //create
			groupToDisplay.setCreatedUser(this.getLoggedUser());
		} else {
			groupToDisplay = GroupDao.getById(groupId); //edit
			groupToDisplay.setCourseId(groupToDisplay.getCourse().getCourseId());
		}

		if (groupId != null && groupToDisplay == null || groupToDisplay.getGroupId() != null
				&& !groupToDisplay.getCreatedUser().equals(getLoggedUser())) {
			return "error"; // access denied
		} else {
			model.addAttribute("group", groupToDisplay);
			model.addAttribute("courses", CourseDao.getCoursesForUser(this.getLoggedUser()));
		}
		return "group/addGroup";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addSubmit(@Valid Group group, BindingResult result, Locale locale, ModelMap model) {
		if(result.hasErrors()){
			return "group/addGroup";
		}
		group.setCourse(CourseDao.getCourseById(group.getCourseId()));
		if (group.getGroupId() == null) {
			GroupDao.add(group);
		} else {
			GroupDao.update(group);
		}
		return "redirect:/group/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Locale locale, @RequestParam(value = "id", required = false) Long courseId) {
		ModelAndView model = new ModelAndView();
		List<Group> groupList = new ArrayList<Group>();
		if (courseId == null) {
			groupList = GroupDao.getForUser(this.getLoggedUser());
			model.addObject("isOwner", true);
		} else {
			Course course = CourseDao.getCourseById(courseId);
			if (course.getUser().equals(getLoggedUser())) {
				groupList = GroupDao.getForCourse(course);
				model.addObject("isOwner", true);
			} else {
				Member member = MemberDao.getMemberForUserAndCourse(getLoggedUser(), course);
				if (member != null) {
					groupList.add(member.getGroup());
					model.addObject("isOwner", false);
				} else {
					model.setViewName("error");
					return model;
				}
			}
		}
		model.addObject("groupList", groupList);
		model.setViewName("group/groupList");
		return model;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Locale locale, @RequestParam(value = "id") int groupId) {
		ModelAndView model = new ModelAndView();
		model.addObject("group", GroupDao.getById(groupId));
		model.setViewName("group/showGroup");
		return model;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(Locale locale, @RequestParam(value = "id") Long p_groupId) {
		Group groupToRemove = GroupDao.getById(p_groupId);
		if (!isCourseOwner(groupToRemove.getCourse())) {
			return new ModelAndView("error");
		}
		GroupDao.remove(p_groupId);
		MemberDao.removeMemberForGroup(p_groupId);
		return new ModelAndView("redirect:/group/list");
	}

	@RequestMapping(value = "/removeMember", method = RequestMethod.GET)
	public ModelAndView removeMember(Locale locale, @RequestParam(value = "id") Long p_memberId) {
		Member memberToRemove = MemberDao.get(p_memberId);
		if (!isCourseOwner(memberToRemove.getGroup().getCourse())) {
			return new ModelAndView("error");
		}
		MemberDao.remove(memberToRemove);
		return new ModelAndView("redirect:/group/list");
	}
	
	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserDao.getUserByEmail(auth.getName());
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.GET)
	public String addMember(Locale locale, @RequestParam(value = "id") Long groupId, ModelMap model) {
		Group group = GroupDao.getById(groupId);
		Course course = group.getCourse();
		if (!isCourseOwner(course)) {
			return "error";
		}
		Member member = new Member();
		member.setGroup(group);
		member.setGroupId(groupId);
		member.setCourse(course);
		model.addAttribute("member", member);
		List<User> availibleUsers = UserDao.getAll();
		availibleUsers.removeAll(GroupDao.getCourseUsers(course));
		availibleUsers.remove(getLoggedUser());
		model.addAttribute("availibleUsers", availibleUsers);
		return "group/addMember";
	}

	private boolean isCourseOwner(Course p_course) {
		return p_course.getUser().equals(getLoggedUser());
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	public String addMemberSubmit(@Valid Member member, BindingResult result, Locale locale, ModelMap model) {
		if (result.hasErrors()) {
			return "group/addMember";
		}
		member.setUser(UserDao.getUserById(member.getUserId()));
		MemberDao.add(member);
		return "redirect:/group/list";
	}

}
