package DAO;

import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.MonHoc;
import util.HibernateUtil;

public class MonHocDAO {
	public static MonHoc layThongTinMonHoc(String maMonHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		MonHoc monHoc = null;
		try {
			monHoc = (MonHoc) session.get(MonHoc.class, maMonHoc);
			session.close();
			return monHoc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}
	
	public static void themMotMonHoc(MonHoc m) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(m);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static List<MonHoc> layDanhSachMonHoc(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<MonHoc> list = null;
		try {
			String hqlString = "from monhoc m ";
			list = session.createQuery(hqlString).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
