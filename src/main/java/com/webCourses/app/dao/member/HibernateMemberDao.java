package com.webCourses.app.dao.member;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCourses.app.model.course.Course;
import com.webCourses.app.model.member.Member;
import com.webCourses.app.model.user.User;

@Repository
public class HibernateMemberDao implements MemberDAO {

	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory p_session) {
		this.sessionFactory = p_session;
	}

	@Override
	public long add(Member p_member) {
		p_member.setAddedDate(new Date());
		getCurrentSession().save(p_member);
		return p_member.getMemberId();
	}

	@Override
	public Member get(long p_memberId) {
		return (Member) getCurrentSession().get(Member.class, p_memberId);
	}

	@Override
	public void update(Member p_member) {
		getCurrentSession().update(p_member);

	}

	@Override
	public void removeMemberForGroup(long p_groupId) {
		Query q = getCurrentSession().createQuery("delete Member where group_id = " + p_groupId);
		q.executeUpdate();
	}

	@Override
	public void remove(Member p_member) {
		getCurrentSession().delete(p_member);
	}

	@Override
	public void remove(long p_memberId) {
		getCurrentSession().delete(this.get(p_memberId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Member getMemberForUserAndCourse(User p_user, Course p_course) {
		Criteria criteria = getCurrentSession().createCriteria(Member.class);
		criteria.add(Restrictions.eq("user", p_user));
		criteria.add(Restrictions.eq("course", p_course));
		List<Member> members = (List<Member>) criteria.list();

		if (members.size() == 1) {
			return members.get(0);
		}
		return null;
	}

}
