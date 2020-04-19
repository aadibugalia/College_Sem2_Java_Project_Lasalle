/**
 * 
 */
package common;

import java.util.regex.Pattern;


/**
 * @author Aditya Bugalia
 * 
 * This class contains all validation methods used in the system. most imp for admin details verification
 *
 */
public class Validations {
	
	// Regex : Valid email or not
		private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		
		// Regex : Valid Display Name or not
		private static final String DISPLAYNAME_PATTERN = "^[A-Za-z0-9_-]{3,15}$";
		
		
		
		
		public static boolean validateEmail(String email) {
			// Compile String regex to pattern
			Pattern pat = Pattern.compile(EMAIL_PATTERN);
			
			if (email == null) {
				return false;
			}
			
			// MAtch regex and return respected boolean value
			return pat.matcher(email).matches();
		}
		
		public static boolean validateDisplayName(String username) {
			// Compile String regex to pattern
			Pattern pat = Pattern.compile(DISPLAYNAME_PATTERN);
			
			if (username == null) {
				return false;
			}
			
			// MAtch regex and return respected boolean value
			return pat.matcher(username).matches();
		}
		
		
		public static boolean validatePassword(String password) {
			
			
			if (password != null) {
				if(password.equalsIgnoreCase(Constants.ADMIN_PASSWORD)) {
					return true;
				}
				
			}
			
			
			return true;
		}

}
