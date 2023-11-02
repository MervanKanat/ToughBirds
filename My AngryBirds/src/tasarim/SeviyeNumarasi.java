
package tasarim;

import java.io.File;

public class SeviyeNumarasi {
    static public int getSeviyeNumarasi() {
		int seviyeNumarasi = 0;
		
		try{
			File harita= new File ("kaynaklar/haritalar");
			seviyeNumarasi = harita.listFiles().length;//(soyut yol adlari dizisi).uzunluk
		}
		catch(Exception e)
		{
			
		}
		return seviyeNumarasi;
    
}
}
