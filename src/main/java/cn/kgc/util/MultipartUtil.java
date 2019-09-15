package cn.kgc.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public class MultipartUtil {
	public static Map<String, Object> name(MultipartFile attach,
			HttpServletRequest request, HttpSession session) {
		// 异常信息提示
		String error = "";

		String idPicPath = null;

		// 图片错误 返回的视图

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		// 判断是否有文件
		if (!attach.isEmpty()) {
			String path = request
					.getSession()
					.getServletContext()
					.getRealPath(
							"statices" + File.separator + "uploade");

			// 判断文件大小
			int fileSize = 5000000;
			if (attach.getSize() > fileSize) {
				error = "文件大小不能超过规定大小！！！";
				request.setAttribute("error", error);
				map.put("error", error);
				return map;
			}
			// 获得文件名称
			String oldName = attach.getOriginalFilename();

			// 获得 文件名的后缀
			String suffix_name = oldName.substring(oldName.indexOf(".")+1,oldName.length());

			// 定义文件新名字
			String newName = "";

			// 判断文件名的后缀
			if (suffix_name.equalsIgnoreCase("jpg")
					|| suffix_name.equalsIgnoreCase("jpeg")
					|| suffix_name.equalsIgnoreCase("gif")
					|| suffix_name.equalsIgnoreCase("png")
					|| suffix_name.equalsIgnoreCase("bmp")) {

				// 定义上传名称
				newName = System.currentTimeMillis()
						+ session.getId().substring(0, 6)
						+  "." + suffix_name;

				File file = new File(path, newName);

				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}

				idPicPath = newName;
			} else {
				error = "文件上传格式不正确，必须是jpg,jpeg,gif,png,bmp格式";
				request.setAttribute("error", error);
				map.put("error", error);
				return map;
			}
		}
		map.put("idPicPath", idPicPath);
		return map;
	}
}
