package com.example.sc2006;

/*public class test {
    public static void main(String[] args){
        Utility util = new Utility();
        MyAccount t = new MyAccount(27192, "Jan", "dummy@com", "Jane", "Stoch", "pAsWs@_12");
        util.fbWrite(t, "MyAccount");
    }
}
*/

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class mock {
    public static void main(String[] args) throws IOException {
        /*
        Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
			int cnt=0;
            @Override
			public void run() {
                CarparkAPIMgr cam = new CarparkAPIMgr();
                CarparkAPI[] APIPtr = new CarparkAPI[1];
                try{
                    APIPtr[0] = cam.fetchCarparkAPI();
                } catch(IOException e){
                    e.printStackTrace();
                }
                System.out.println(APIPtr[0].timestamp + "\n");
                for(int i=0;i<5;i++){
                    System.out.print("carpark_number: ");
                    System.out.println(APIPtr[0].carpark_data_arr.get(i).carpark_number);
                    System.out.print("lots_available: ");
                    System.out.println(APIPtr[0].carpark_data_arr.get(i).lots_available);
                    System.out.println();
                }
                cnt++;
                if ( cnt <= 2 ) {
					try {
						Thread.sleep(1000 * 60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if ( cnt >= 8 ) {
					timer.cancel();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000 * 60);
	}
    */

        Utility util = new Utility();
        MyAccount myaccount = new MyAccount(28102, "Jas", "ntunus@smu.com", "Justin", "Brown", "pAss@o_4");
        util.fbWrite(myaccount, "MyAccount", 1);
    }
}