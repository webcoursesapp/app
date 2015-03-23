package com.webCourses.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.webCourses.app.dao.group.GroupDAO;
import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.group.Group;
import com.webCourses.app.model.user.User;

public class ApplicationUtils {
	
	public static final String urlPath = "http://localhost:8080/app/";
	public static final String urlPathWithResources = urlPath + "resources/";

	public static String getMd5FromString(String p_password) {

		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(p_password.getBytes());
			byte[] digest = md.digest();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	@SuppressWarnings("deprecation")
	public static String getRealPathForDirectory(String p_directory) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		String realPath = requestAttributes.getRequest().getRealPath("/" + p_directory);
		return realPath;
	}
	
	public static boolean isCourseMember(GroupDAO groupDao, Course p_course, User p_user) {
		List<Group> groups = groupDao.getForCourse(p_course);
		for (Group group : groups) {
			if (group.getMembers().contains(p_user)) {
				return true;
			}
		}
		return false;
	}
}
