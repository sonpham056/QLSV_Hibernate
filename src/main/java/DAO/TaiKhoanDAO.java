package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.BangDiem;
import entities.TaiKhoan;
import util.HibernateUtil;

public class TaiKhoanDAO {
	public static TaiKhoan layThongTinTaiKhoan(String taiKhoan) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TaiKhoan t = null;
		try {
			t = (TaiKhoan) session.get(TaiKhoan.class, taiKhoan);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return t;
	}
	
	public static void themMotTaiKhoan(TaiKhoan taiKhoan) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		try {
			session.save(taiKhoan);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public static void capNhatTaiKhoan(TaiKhoan taiKhoan) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		try {
			session.update(taiKhoan);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
}
