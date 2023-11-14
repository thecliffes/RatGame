package RatGame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/**
 * This class solves a puzzled string and returns the message of the day.
 * @author Carolina Duarte and James Butler
 * @version 1
 */
public class MessageOfTheDay {

    /**
     * Decodes the requested string from the HTTP.
     * @param S The puzzle string from the HTTP request.
     * @return The decoded string.
     */
    public static String shiftCharacters(String S) {

        String alphabetList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";

        char[] sToChar = S.toCharArray();

        for (int i = 0; i < sToChar.length; i++) {
            int index = alphabetList.indexOf(sToChar[i]);
            if (i % 2 == 0) {
                result = result + alphabetList.charAt((((index  - (i+1)) % 26) + 26) % 26);
            } else {
                result = result + alphabetList.charAt((((index  + (i+1)) % 26) + 26) % 26);
            }
        }
        return result;
    }

    /**
     * Sends out an HTTP Get request to the specific URL in order to retrieve an encoded string.
     * @return A puzzle string.
     * @throws Exception Will throw an exception if there is a failure caused by input/output, or if there is
     * a problem establishing the connection (eg. connection timeout).
     */
    public static String connectionRequest () throws Exception{
        URL newUrl = new URL("http://cswebcat.swansea.ac.uk/puzzle");
        URLConnection newUrlC = newUrl.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        newUrlC.getInputStream()));
        String inputLine;
        String result = null;
        while ((inputLine = in.readLine()) != null) {
            result = shiftCharacters(inputLine) + "CS-230";
            result = result.length() + result;
        }
        in.close();
        return result;
    }

    /**
     * Sends out another HTTP Get request with the decoded puzzle string,
     * this time to a different URL in order to retrieve the message of the day.
     * @return The message of the day.
     * @throws Exception Will throw an exception if there is a failure caused by input/output, or if there is
     * a problem establishing the connection (eg. connection timeout).
     */
    public static String connectionRequestWithSolution () throws Exception{
        URL newUrlWithSolution = new URL("http://cswebcat.swansea.ac.uk/message?solution="+connectionRequest());
        URLConnection newUrlC = newUrlWithSolution.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        newUrlC.getInputStream()));
        String inputLine = in.readLine();
        in.close();
        return inputLine;
    }

    public static String getMotd() throws Exception {
        String motd = connectionRequestWithSolution();
        // TODO: Trim end bit.
        return motd.trim();
    }

    public static void main(String[] args) {
        try {
            System.out.println(connectionRequestWithSolution());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}