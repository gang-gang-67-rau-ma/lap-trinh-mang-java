package dau_2251220146;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class doc_file {
    // Hàm đọc file, trả về danh sách các dòng (List<String>)
    public static List<String> docFile(String fileName) {
        List<String> lines = new ArrayList<>(); // Danh sách chứa các dòng đọc được
        File file = new File(fileName);

        try {
            // Kiểm tra nếu file tồn tại và không rỗng
            if (file.exists() && file.length() > 0) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                // Đọc từng dòng và thêm vào danh sách
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }

                br.close(); // đóng file sau khi đọc xong
            } else {
                // Nếu file không tồn tại hoặc rỗng, thêm thông báo vào danh sách
                lines.add("File khong ton tai hoac rong");
            }
        } catch (Exception e) {
            // Nếu có lỗi, thêm thông báo lỗi vào danh sách
            lines.add("Loi doc file: " + e.getMessage());
        }

        return lines; // Trả về danh sách các dòng đã đọc
    }
}
