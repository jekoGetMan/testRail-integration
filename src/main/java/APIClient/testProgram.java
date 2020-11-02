package APIClient;

public class testProgram {
    public static void main(String[] args) throws Exception {

        API_Client client = new API_Client("https://testrail.refinitiv.com/testrail", "login", "password");
//        System.out.println(client.sendGet("get_case/31477741"));

    }
}