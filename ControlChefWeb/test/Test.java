
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 201519060360
 */
public class Test {

    public Test() {
    }

    public void ai() {
        URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
    }

    public void main(String[] args) {
        ai();
    }
}
