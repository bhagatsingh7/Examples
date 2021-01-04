// PERAMETER RELATED METHODS
	public static boolean isEmpty(String str) {
		try {
			if (str == null || str.isEmpty()) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean isAllEmpty(String... fields) {
		try {
			for (String s : fields) {
				if (s != null && !s.isEmpty())
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean isAnyEmpty(String... fields) {
		try {
			for (String s : fields)
				if (s == null || s.isEmpty())
					return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean checkBlankScriptLength(String str, int lenght) {
		try {
			if (StringUtils.isBlank(str))
				return true;

			if (str.trim().length() > lenght)
				return true;

			if (str.toLowerCase().contains("<script>"))
				return true;

			if (str.toLowerCase().contains("</script>"))
				return true;

			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean checkBlankScriptLengthEmail(String str, int lenght) {
		try {

			if (checkBlankScriptLength(str, lenght))
				return true;

			if (!str.contains("@"))
				return true;

			if (!str.contains("."))
				return true;

			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// DATABASE RELATED METHODS
	public static void closeConection(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// FILE RELATED METHOD
	public static boolean notPdf(MultipartFile multipartFile) {
		try {
			String originalfileName = multipartFile.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalfileName);

			System.out.println("BOUT=ORIGIANAL=>" + originalfileName);
			System.out.println("BOUT=EXTENSION=>" + extension);

			if (extension.equalsIgnoreCase("pdf")) {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean bigSizePdf(MultipartFile multipartFile, int mb) {
		try {
			// Get length of file in bytes
			long fileSizeInBytes = multipartFile.getSize();
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			long fileSizeInKB = fileSizeInBytes / 1024;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			long fileSizeInMB = fileSizeInKB / 1024;

			if (fileSizeInMB > mb) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean sendEmail(String emailSendTo, String emailBodyMessage, byte[] attachment) {
		try {
			// =================================== EMAIL ============================
			final String emailFrom = "dgftedi@nic.in"; // requires valid gmail id
			final String emailPass = "Dgf@2015$"; // correct password for gmail id
			final String emailSubject = "Directorate General of Foreign Trade Unique Reference Number(URN) Registration";
			// String emailSendTo = sendto;
			// String emailBodyMessage = message;
			// ByteArrayOutputStream baos = attachment;

			System.out.println("TLSEmail Start");

			// Settings for DGFT NIC Email
			Properties props = new Properties();
			props.put("mail.smtp.host", "relay.nic.in"); // SMTP Host
			props.put("mail.smtp.port", "25"); // TLS Port
			props.put("mail.smtp.auth", "true"); // enable authentication
			props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

			// Settings for GMAIL
			// props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
			// props.put("mail.smtp.port", "587"); // TLS Port
			// props.put("mail.smtp.auth", "true"); // enable authentication
			// props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

			// Email logic
			// create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailFrom, emailPass);
				}
			};
			Session sessionEmail = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(sessionEmail);

			// Email content setting
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("dgftedi@nic.in", "DGFT"));
			msg.setReplyTo(InternetAddress.parse(emailFrom, false));
			msg.setSubject(emailSubject, "UTF-8");

			// msg.setText(body, "UTF-8");
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Now set the actual message
			messageBodyPart.setText(emailBodyMessage);

			// Create a multipart message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			if (attachment != null) {
				// Part two is attachment
				DataSource aAttachment = new ByteArrayDataSource(attachment, "application/octet-stream");
				messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(aAttachment));
				messageBodyPart.setFileName("IEC_Certificate.pdf");
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the complete message parts
			msg.setContent(multipart);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailSendTo, false));
			Transport.send(msg);

			System.out.println("BOUT==>EMail Sent Successfully!!");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int getRandom() {
		try {
			return ThreadLocalRandom.current().nextInt(1, 89990) + 10000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static long getRandom(int digits) {
		try {
			String n = "1";
			for (int i = 0; i < digits-1; i++) {
				n += "0";
			}
			long dn = Long.valueOf(n);
			return ThreadLocalRandom.current().nextInt(1, (int) dn) + dn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
