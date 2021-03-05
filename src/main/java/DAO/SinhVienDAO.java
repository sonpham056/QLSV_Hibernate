package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.tree.SessionFactoryAwareNode;
import org.hibernate.query.Query;

import entities.BangDiem;
import entities.Lop;
import entities.MonHoc;
import entities.SinhVien;
import util.HibernateUtil;

public class SinhVienDAO {
	public static SinhVien layThongTinSinhVien(String maSV) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SinhVien sinhVien = null;
		try {
			sinhVien = (SinhVien) session.get(SinhVien.class, maSV);
			session.close();
			return sinhVien;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public static void themMotSinhVien(SinhVien s) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(s);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public static void suaMotSinhVien(SinhVien s) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(s);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public static void xoaMotSinhVien(SinhVien s) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.remove(s);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SinhVien> layDanhSachSVThuocLop(Lop lop) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<SinhVien> list = null;
		try {
			String hqlString = "from sinhvien sv where sv.lop = :Lop ";
			Query query = session.createQuery(hqlString).setParameter("Lop", lop);
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
	
	public static List<SinhVien> layDanhSachSVThuocLopVaMonHoc(Lop lop, MonHoc monHoc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<SinhVien> list = null;
		try {
			String hqlString = "select sv from sinhvien sv, bangdiem bd where sv.lop = ?0 and sv.maSinhVien = bd.sinhVien and bd.monHoc = ?1";
			Query query = session.createQuery(hqlString).setParameter(0, lop).setParameter(1, monHoc);
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
}
