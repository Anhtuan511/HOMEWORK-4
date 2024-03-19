package Homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong hoc vien: ");
        int n = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        // Nhập và ghi ra file danh sách học viên
        try (PrintWriter writer = new PrintWriter(new FileWriter("input.txt"))) {
            for (int i = 0; i < n; i++) {
                System.out.println("Nhap thong tin hoc vien thu " + (i + 1) + ":");
                System.out.print("Ma sinh vien: ");
                String maSV = scanner.next();
                System.out.print("Ho ten: ");
                String hoTen = scanner.next();
                System.out.print("Gioi tinh: ");
                String gioiTinh = scanner.next();
                System.out.print("Diem Python: ");
                double diemPython = scanner.nextDouble();
                System.out.print("Diem Java: ");
                double diemJava = scanner.nextDouble();

                writer.println(maSV + ", " + hoTen + ", " + gioiTinh + ", " + diemPython + ", " + diemJava);
                students.add(new Student(maSV, hoTen, gioiTinh, diemPython, diemJava));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Đọc dữ liệu từ file và thực hiện các công việc
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                Student student = new Student(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sắp xếp học viên theo điểm trung bình giảm dần
        Collections.sort(students, Comparator.comparingDouble((Student s) -> s.diemTB).reversed());

        // In danh sách học viên và ghi ra file
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            for (Student student : students) {
                writer.println(student);
                System.out.println(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Nhập họ tên học viên cần tìm
        System.out.print("Nhap ho ten hoc vien can tim: ");
        String searchName = scanner.next();

        // Tìm kiếm học viên và hiển thị thông tin
        boolean found = false;
        for (Student student : students) {
            if (student.hoTen.equalsIgnoreCase(searchName)) {
                System.out.println("Thong tin hoc vien can tim: " + student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay hoc vien co ho ten " + searchName);
        }

        // Hiển thị thông tin những bạn đã đậu
        System.out.print("Thong tin nhung ban da dau: ");
        Set<String> passedStudents = new HashSet<>();
        for (Student student : students) {
            if (student.ketQua.equals("Dau") && passedStudents.add(student.toString())) {
                System.out.print(student + " ");
            }
        }
        System.out.println();

        // Hiển thị thông tin những bạn đã trượt
        System.out.print("Thong tin nhung ban da truot: ");
        Set<String> failedStudents = new HashSet<>();
        for (Student student : students) {
            if (student.ketQua.equals("Rot") && failedStudents.add(student.toString())) {
                System.out.print(student + " ");
            }
        }
        System.out.println();

        // Hiển thị thông tin những bạn có điểm trung bình >= 8
        System.out.print("Thong tin nhung ban co diem trung binh >= 8: ");
        Set<String> highScoreStudents = new HashSet<>();
        for (Student student : students) {
            if (student.diemTB >= 8 && highScoreStudents.add(student.toString())) {
                System.out.print(student + " ");
            }
        }
        System.out.println();
    }
}