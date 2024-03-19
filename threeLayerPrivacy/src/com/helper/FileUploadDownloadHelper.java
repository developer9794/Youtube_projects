package com.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.helper.UserModel;
import com.database.ConnectionManager;
import com.constant.ServerConstants;
import com.helper.StringHelper;

/**
 * @author admin
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FileUploadDownloadHelper {

	public static String uploadFile(InputStream uploadInStream, String filename) {
		try {
			System.out.println(filename);
			FileOutputStream fOut = new FileOutputStream(filename);
			int c = 0;
			while ((c = uploadInStream.read()) != -1) {
				fOut.write(c);
			} // while
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		File uploadedFile = null;
		try {
			InputStream fis = new FileInputStream(
					uploadedFile.getAbsolutePath());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*public static boolean uploadFile(HttpServletRequest request, String path,
			UserModel um, String ftype) {
		// String path = ServerConstants.upload_directory;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		FileUploadDownloadHelper fdh = new FileUploadDownloadHelper();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// Set factory constraints
		// factory.setSizeThreshold(30);
		String s = "";
		// factory.setRepository(yourTempDirectory);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		// upload.setSizeMax(yourMaxRequestSize);
		// FileUploadProgressListener fupl = new FileUploadProgressListener();
		// upload.setProgressListener(fupl);
		// Parse the request
		List items;
		try {
			items = upload.parseRequest(request);

			// Process the uploaded items
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				String name = item.getFieldName();
				InputStream stream = item.getInputStream();
				System.out.println(item.getSize());
				if (item.isFormField()) {
					String loc = Streams.asString(stream);
					if (name.equalsIgnoreCase("ftype")) {
						System.out.println(name + "==" + loc);
					}
				} else {
					request.setAttribute("FILE_NAME", item.getName());
					System.out.println("File field " + name
							+ " with file name " + item.getName()
							+ " detected.");
					if (item.getName() != null && item.getName() != "") {
						path = path.replace("\\", "/");
						String fname = item.getName().substring(
								item.getName().lastIndexOf("/") + 1);
						System.out.println(item.getName().substring(
								item.getName().lastIndexOf("/") + 1));
						path += fname;
						System.out.println("Uploading files to " + path);
						System.out.println(">>>>>>>>>>>>>> Modifted path "
								+ path);
						System.out.println(">>>>>>>>>>>>>> Path to upload to "
								+ path);
						// String completeFilePath = path;
						boolean b = fdh.uploadFile2(stream, path);
						String flvpath = ServerConstants.FLVPATH + fname;
						flvpath = flvpath
								.substring(0, flvpath.lastIndexOf("."))
								+ "."
								+ ftype;
						new VideoConvertor().convertVideo(path, flvpath);
						ConnectionManager.saveFile(flvpath, um.getUid());
						return b;
					}
				}
			}
			// arg0.getRequestDispatcher("/UserRegistrationServlet?methodId=getUserAttachments").forward(arg0,
			// arg1);
			//
			// arg1.sendRedirect(arg0.getContextPath()+"/website/pages/upload.jsp?tp="+new
			// Date());
			// arg0.getRequestDispatcher("/website/pages/upload.jsp?tp="+new
			// Date()).forward(arg0, arg1);
		} catch (org.apache.commons.fileupload.FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}*/

	public static void mains(String arg[]) {

	}

	/*public static boolean saveFile(HashMap parameters, HttpSession session) {
		try {
			FileItem fi = (FileItem) parameters.get("ITEM");
			System.out.println(parameters);

			String filename = StringHelper.n2s(parameters.get("videoName"));
			String ftype = StringHelper.n2s(parameters.get("ftype"));
			UserModel model = (UserModel) session.getAttribute("USER_MODEL");
			File file = new File(ServerConstants.UPLOADPATH + filename);
			System.out.println(file.getAbsolutePath());

			fi.write(file);
			String flvpath = ServerConstants.FLVPATH + filename;
			flvpath = flvpath.substring(0, flvpath.lastIndexOf(".")) + "."
					+ ftype;
			new VideoConvertor().convertVideo(file.getAbsolutePath(), flvpath);
			ConnectionManager.saveFile(flvpath, model.getUid());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}*/

	public static HashMap parseMultipartRequest(HttpServletRequest request) {

		System.out.println("Multipart Parser Start");

		HashMap param = new HashMap();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println(isMultipart);
		String inputFile = "", outputFile = "";
		if (!isMultipart) {
			System.out.println("File Not Uploaded");
		} else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;
			String uid = "", desc = "";
			try {
				items = upload.parseRequest(request);
				System.out.println("items: " + items);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				// textbox checkbox
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = StringHelper.n2s(item.getString());
					param.put(name, value);

				} else {
					// file
					String itemName = item.getName();
					param.put(item.getFieldName(), item.getName());
					try {
						param.put(item.getFieldName() + "FILE",
								item.getInputStream());
						param.put("ITEM", item);

						param.put(item.getFieldName() + "_FILE_CTYPE",
								item.getContentType());

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		System.out.println(param);
		return param;

	}

	public boolean uploadFile2(InputStream uploadInStream, String filename) {
		try {
			System.out.println(filename);
			FileOutputStream fOut = new FileOutputStream(filename);
			int c = 0;
			while ((c = uploadInStream.read()) != -1) {
				fOut.write(c);
			} // while
			fOut.flush();
			fOut.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Returns the contents of the file in a byte array.
	public static byte[] getBytesFromFile(File file) {
		if (file.exists()) {
			InputStream is;
			byte[] bytes = null;
			try {
				is = new FileInputStream(file);

				// Get the size of the file
				long length = file.length();

				// You cannot create an array using a long type.
				// It needs to be an int type.
				// Before converting to an int type, check
				// to ensure that file is not larger than Integer.MAX_VALUE.
				if (length > Integer.MAX_VALUE) {
					// File is too large
				}

				// Create the byte array to hold the data
				bytes = new byte[(int) length];

				// Read in the bytes
				int offset = 0;
				int numRead = 0;
				while (offset < bytes.length
						&& (numRead = is.read(bytes, offset, bytes.length
								- offset)) >= 0) {
					offset += numRead;
				}

				// Ensure all the bytes have been read in
				// if (offset < bytes.length) {
				// throw new
				// IOException("Could not completely read file "+file.getName());
				// }

				// Close the input stream and return bytes
				is.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

			return bytes;
		} else {
			return null;
		}
	}

	public static void downloadFile(String filePath,HttpServletResponse response) throws IOException 
	{
		OutputStream os=response.getOutputStream();
		try {
			
			File file = new File(filePath);
			response.setContentType("application/octet-stream");  //stands for binary data
			response.setHeader("Content-Disposition", "attachment;filename="+ file.getName());//This says "Don't even try to show the file, just save the file." It also suggests a filename
			
			System.out.println("--------------------");
			System.out.println(file.getName());
			System.out.println("----------------------");
			InputStream in = new FileInputStream(file.getAbsolutePath());
			byte[] outputByte = new byte[1024];
			// copy binary contect to output stream
			while (true) {   
				int read = in.read(outputByte, 0, 1024);
				if (read == -1)
					break;
				os.write(outputByte, 0, read);
			}
			in.close();
			os.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			os.close();
		}
	}

}
