package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.Lop;
import util.HibernateUtil;

public class LopDAO {
	public static Lop layThongTinLop(String lop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Lop l = null;
		try {
			l = (Lop) session.get(Lop.class, lop);
			session.close();
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}
	
	public static List<Lop> layDanhSachLop(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Lop> list = null;
		try {
			String hql = "select l from lop l";
			Query query = session.createQuery(hql);
			list = query.list();
			session.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public static void themMotLop(Lop l) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(l);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
}
