//SIMPLE SHA-512 ENCRYPTION WITHOUT ANY DEPENDENCY
private String get_SHA_512(String str) {
		String encrStr = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			byte[] bytes = md.digest(str.getBytes("UTF-8"));

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			encrStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encrStr;
	}
