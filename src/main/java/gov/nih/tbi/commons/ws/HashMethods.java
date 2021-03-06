package gov.nih.tbi.commons.ws;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMethods {

  private static final String HASH_VALUE = "SHA-256";
  private static final String BYTE_VALUE = "UTF-8";

  protected static final String SERVER_TO_CLIENT_SALT = "S*#HOfadf#%*DAk;";
  protected static final String CLIENT_TO_SERVER_SALT = "noFE(34nk;97&few";

  protected static final Integer NUMBER_OF_HASH_ATTEMPTS = 1000;

  public static String getServerHash(String... args) {

    String temp = getHash(SERVER_TO_CLIENT_SALT, args);

    for (int i = 0; i < NUMBER_OF_HASH_ATTEMPTS; i++) {
      temp = getHash(SERVER_TO_CLIENT_SALT, temp);
    }

    return temp;
  }

  public static String getClientHash(String... args) {

    String temp = getHash(CLIENT_TO_SERVER_SALT, args);

    for (int i = 0; i < NUMBER_OF_HASH_ATTEMPTS; i++) {
      temp = getHash(CLIENT_TO_SERVER_SALT, temp);
    }

    return temp;
  }

  public static String getHash(String privateKey, String... args) {

    try {
      MessageDigest sha = MessageDigest.getInstance(HASH_VALUE);

      sha.update(privateKey.getBytes(BYTE_VALUE));

      for (String arg : args) {
        sha.update(arg.getBytes(BYTE_VALUE));
      }

      byte[] hash = sha.digest();
      BigInteger bigInt = new BigInteger(1, hash);
      String output = bigInt.toString(16);

      return output;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    throw new RuntimeException("Failed to create Hash");
  }

  public static boolean validateServerHash(String hashCode, String... args) {

    return getServerHash(args).equals(hashCode);
  }

  public static boolean validateClientHash(String hashCode, String... args) {

    return getClientHash(args).equals(hashCode);
  }

  public static String convertFromByte(byte[] hashCode) {

    BigInteger bigInt = new BigInteger(1, hashCode);
    return bigInt.toString(16);
  }

  public static void main(String[] args) {

    String username = "administrator";
    String password = "_GGt<qit>y6GP.+_Nlz>!%.L`xoyZ7";
    String hashCode = null;
    Boolean test = null;

    password = HashMethods.getHash(password, args);

    hashCode = HashMethods.getServerHash(username);
    test = HashMethods.validateServerHash(hashCode, username);

    System.out.println("HashCode: '" + hashCode + "', " + test);

    hashCode = HashMethods.getClientHash(username);
    test = HashMethods.validateClientHash(hashCode, username);

    System.out.println("HashCode: '" + hashCode + "', " + test);

    // Calculate Password

    password = HashMethods.getServerHash(username, password);

    System.out.println("password: '" + password + "'");

  }
}
