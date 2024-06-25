package com.quach.shop_giay.controler;

import com.quach.shop_giay.database.UserDAO;
import com.quach.shop_giay.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/changeAvatar")
public class changeAvatar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/changeAvatar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("123");
        Object obj = request.getSession().getAttribute("user");
        User user = null;
        if (obj != null)
            user = (User) obj;
        if (user != null) {
            try {
                String folder = getServletContext().getRealPath("/img/avatar");
                System.out.println(folder);
                File file;
                int maxFileSize = 5000 * 1024;
                int maxMemSize = 5000 * 1024;

                String contentType = request.getContentType();

                if (contentType.indexOf(contentType) >= 0) {
                    DiskFileItemFactory factory = new DiskFileItemFactory();

                    // Quy dinh dung luong toi da cho 1 file
                    factory.setSizeThreshold(maxMemSize);

                    // Tao file upload
                    ServletFileUpload upload = new ServletFileUpload(factory);

                    upload.setSizeMax(maxFileSize);

                    List<FileItem> files = upload.parseRequest((javax.servlet.http.HttpServletRequest) request);

                    for (FileItem fileItem : files) {
                        if(!fileItem.isFormField()) {
                            String fileName = System.currentTimeMillis() + fileItem.getName();
                            String path = folder + "\\" + fileName;
                            file = new File(path);

                            fileItem.write(file);

                            user.setAvatar(fileName);
                            UserDAO userDAO = new UserDAO();
                            userDAO.updateAvatar(user);
                            user = userDAO.getId(user);
                            System.out.println(fileName);
                        }else {
                            String name = fileItem.getFieldName();
                            String value = fileItem.getString();
                            System.out.println(name+" : "+value);
                        }
                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
