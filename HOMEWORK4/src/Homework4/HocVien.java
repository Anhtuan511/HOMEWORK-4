package Homework4;

import java.io.*;
import java.util.*;

class Student {
    String maSV;
    String hoTen;
    String gioiTinh;
    double diemPython;
    double diemJava;
    double diemTB;
    String ketQua;

    public Student(String maSV, String hoTen, String gioiTinh, double diemPython, double diemJava) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diemPython = diemPython;
        this.diemJava = diemJava;
        this.diemTB = (diemJava * 2 + diemPython) / 3;
        if (diemTB >= 5) {
            this.ketQua = "Dau";
        } else if (diemTB < 5 && gioiTinh.equalsIgnoreCase("Nam")) {
            this.ketQua = "Truot";
        } else {
            this.ketQua = "Dau";
        }
    }

    @Override
    public String toString() {
        return maSV + ", " + hoTen + ", " + gioiTinh + ", " + diemPython + ", " + diemJava + ", " + diemTB + ", " + ketQua;
    }
}
