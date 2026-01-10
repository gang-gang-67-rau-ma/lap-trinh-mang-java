package tcp-vip;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ghi_file {
    // Hàm ghi dữ liệu vào file
    public static void ghiFile(String data, String fileName) {
        try (
                // Tạo FileWriter với chế độ append = true (ghi tiếp vào cuối file, không xóa dữ
                // liệu cũ)
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            // Ghi nội dung data vào file
            bw.write(data);

            // Xuống dòng sau khi ghi (mỗi lần ghi 1 dòng riêng)
            bw.newLine();

            // Thông báo ra console để dễ theo dõi
            System.out.println("Đã ghi vào file: " + fileName + " nội dung: " + data);
        } catch (Exception e) {
            // Nếu có lỗi thì in ra để debug
            e.printStackTrace();
        }
    }
}
