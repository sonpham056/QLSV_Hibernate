package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.BangDiem;
import entities.MonHoc;
import entities.SinhVien;
import util.HibernateUtil;

public class BangDiemDAO {
	public static BangDiem layThongTinBangDiem(SinhVien s, MonHoc m) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		BangDiem bangDiem = null;
		try {
			bangDiem = (BangDiem) session.get(BangDiem.class, new BangDiem(s, m));
			session.close();
			return bangDiem;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public static void themMotBangDiem(BangDiem bangDiem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(bangDiem);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	
	public static void xoaMotBangDiem(BangDiem bangDiem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.remove(bangDiem);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public static void capNhatMotBangDiem(BangDiem bangDiem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(bangDiem);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	public static List<BangDiem> layDanhSachSinhVienTheoMon(MonHoc monHoc){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<BangDiem> list = null;
		try {
			String hqlString = "from bangdiem b where b.monHoc = :monHoc ";
			Query query = session.createQuery(hqlString).setParameter("monHoc", monHoc);
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
	
	public static List<BangDiem> layDanhSachBangDiemTheoSinhVien(SinhVien sinhVien){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<BangDiem> list = null;
		try {
			String hql = "from bangdiem bd where bd.sinhVien = :sinhVien";
			list = session.createQuery(hql).setParameter("sinhVien", sinhVien).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}
