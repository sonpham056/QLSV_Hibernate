package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.CTMonHoc;
import entities.Lop;
import util.HibernateUtil;

public class CTMonHocDAO {
	public static CTMonHoc layThongTinCTMH(String maMonHoc, String maLop, String phongHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CTMonHoc ctMonHoc = null;
		try {
			ctMonHoc = (CTMonHoc) session.get(CTMonHoc.class, new CTMonHoc(maMonHoc, maLop, phongHoc));
			session.close();
			return ctMonHoc;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public static void themMotCTMH(CTMonHoc ctMonHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(ctMonHoc);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static List<CTMonHoc> layDanhSachCTMonHocTheoLop(Lop lop){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<CTMonHoc> list = null;
		try {
			String hqlString = "from ctmonhoc c where c.lop = :lop";
			list = session.createQuery(hqlString).setParameter("lop", lop).list();
			session.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
