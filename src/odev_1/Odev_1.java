/**
*
* @author Furkan KOÇ - G171210303 - furkan.koc8@ogr.sakarya.edu.tr
* @author Muzaffer Haşim GEZGİN - G151210123 - muzaffer.gezgin@ogr.sakarya.edu.tr
* @since 01/03/2019
* <p>
* Programlama Dilleri Prensipleri dersi 1. ÖDEVİ
* </p>
*/
package odev_1;

import java.io.*; //Kütüphaneleri ekliyoruz.
import java.util.*;



public class Odev_1 
{
public static ArrayList Fonksiyonlar = new ArrayList(); //Fonksiyon isimlerimizi tutacağımız bir ArrayList oluşturuyoruz.
public static ArrayList Parametreler = new ArrayList(); //Parametre isimlerimizi tutacağımız bir ArrayList oluşturuyoruz.
public static ArrayList Parametreler2 = new ArrayList(); //Parametre isimlerimizi veri türü olmadan tutacağımız bir ArrayList oluşturuyoruz.
public static ArrayList<Integer> tireIndis = new ArrayList<Integer>(); //Satırdaki tirelerin indekslerini tutacağımız bir ArrayList oluşturuyoruz.
public static ArrayList<Integer> virgulIndis = new ArrayList<Integer>();  //Satırdaki virgüllerin indekslerini tutacağımız bir ArrayList oluşturuyoruz.
public static int ParametreSayisi=0; //Toplam Parametre sayısını tutacağımız indisimizi oluşturuyoruz.

   public static int satir_Sayisi() //Okuduğumuz C dosyasının satır sayısını bulacak fonksiyonumuzu oluşturuyoruz.
    {
    int sayac=0;
        try{
           FileInputStream fStream = new FileInputStream("Program.c"); // C dosyasını okuma işlemlerini yapıyoruz.
           DataInputStream dStream = new DataInputStream(fStream);
           BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));
           
           while (bReader.readLine() != null) { 
               sayac++; //Satır sayısı kadar arttırıyoruz.
           }
           dStream.close();
       }
       catch(Exception e){
           System.err.println("Hata : " + e.getMessage());
       }
    return sayac;
    }
   
   public void Fonksiyon(String s) //Fonksiyon olan satırlarda işlem yapacağımız bir fonksiyon tanımlıyoruz.
   {
        
        String boslukDuzeltme = s.replaceAll("\\s+", " "); //Bütün boşlukları tek boşluğa çeviriyoruz.
        String y = boslukDuzeltme.replaceAll(" ", "-"); //Boşlukları tireye çeviriyoruz.
        int a = y.indexOf("-");//Tirenin indeksini buluyoruz.
        int b = y.indexOf("(");//Parantez açmanın indeksini buluyoruz.
        int c = y.indexOf(")");//Parantez kapamanın indeksini buluyoruz.
        String parametreler = y.substring(b + 1, c); //parametrelerimizi satırdan kesiyoruz.
        
        Parametreler.add(parametreler + ","); //Parametreler ArrayList'ine parametrelerimizi atıyoruz.
        String FonksiyonAdi = y.substring(a+1,b); //Fonksiyon adımızı satırdan kesiyoruz.
        Fonksiyonlar.add(FonksiyonAdi); //Fonksiyonlar ArrayList'ine fonksiyon adımızı atıyoruz.
        if (parametreler.length() <= 1) {//kaç tane parametremiz olduğunu hesapladığımız bir if-else if-else yapısı kuruyoruz.
           ParametreSayisi += 0;
       }
        else if (parametreler.contains(",") == false && parametreler.length() >= 1) {
           ParametreSayisi++;
       }
        else {
            String []prmtArray = parametreler.split(",");
            for (int i = 0; i < prmtArray.length; i++) {
                ParametreSayisi++;
            }
       }
   }
   
   public void tireVirgulBul(String s) //Tire ve virgüllerimizin indekslerini ArrayListlerimize atacağımız bir fonksiyon oluşturuyoruz.
   {
       for (int i = 0; i < s.length(); i++) 
       {
           if (s.charAt(i)=='-') 
           {
               tireIndis.add(i);
           }
           if (s.charAt(i)==',') 
           {
               virgulIndis.add(i);
           }
       }
   }
   
   public void parametreDuzenle(String tmp)//Parametrelerimizin veri türü olmadan halini alacağımız bir fonksiyon tanımlıyoruz.
   {
		String parametre = new String();
		tireVirgulBul(tmp);
		for(int i = 0; i<tireIndis.size(); i++)
		{
                    parametre+=tmp.substring(tireIndis.get(i)+1, virgulIndis.get(i))+",";//Tire ve virgüllerin indekslerinen yararlanarak sadece parametre isimlerini ayırdık.
		}
		parametre = parametre.substring(0, parametre.length());
		Parametreler2.add(parametre);
		tireIndis.clear();
		virgulIndis.clear();
		
   }
   
    public static void main(String[] args) 
    {
       Odev_1 cls = new Odev_1(); //Sınıftan nesne türetiyoruz.
       String str; //Gerekli değişken tanımlamalarını yapıyoruz.
       String satir2;
       int satirsayisi=satir_Sayisi();
       int fonksiyonsayisi = 0;
       int operatorsayisi = 0;
       int operatorsayisi2 = 0;
       String harf;
       String harf2;
       String metin;
       
       ArrayList<String> veri = new ArrayList<String>(); //veri adında ArrayList oluşturuyoruz.
       
       try{
           FileInputStream fStream = new FileInputStream("Program.c");
           DataInputStream dStream = new DataInputStream(fStream);
           BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));
        
           while ((str = bReader.readLine()) != null) 
           { 

               veri.add(str); //okuduğumuz satırları veri ArrayList'ine atıyoruz.
               
               satir2=str;
               
               if ((str.contains("void") || str.contains("char") || str.contains("int") || str.contains("boolean") || str.contains("String") || str.contains("double") || str.contains("float")) && str.contains(")") && str.contains(";") == false) 
               {
                   cls.Fonksiyon(satir2);
                   //Eğer satırda bir fonksiyon tanımlaması varsa o satırı Fonksiyon fonksiyonuna gönderiyoruz.
               }
           }
           dStream.close();
       }
       
       
       catch(Exception e){
           System.err.println("Hata : " + e.getMessage());
       }
       
       
        for (int i = 0; i < Parametreler.size(); i++) {
           String parametre = Parametreler.get(i).toString();
           cls.parametreDuzenle(parametre); //Parametreleri veri türünden ayırmak için parametreDuzenle fonksiyonuna gönderiyoruz.
        }
      
        for (int i = 0; i < veri.size(); i++) {
            if (veri.get(i).contains("void") || veri.get(i).contains("double") || veri.get(i).contains("boolean") || veri.get(i).contains("float") || veri.get(i).contains("String") || veri.get(i).contains("int")&& veri.get(i).contains("(") && veri.get(i).contains(")") && veri.get(i).contains(";")==false) {
                fonksiyonsayisi++; //Eğer okuduğumuz satırda fonksiyon varsa fonksiyon sayısını 1 arttırıyoruz.
            }
        }
        
        String []Satirlar = new String[satirsayisi];//Program.c nin içindeki satır sayısı kadarlık bir dizi oluşturuyoruz.
        for (int i = 0; i < veri.size(); i++) {
           Satirlar[i]=veri.get(i);//okuduğumuz her satırı satırlar adındaki dizinin bir indisine atıyoruz.
        }
        
        for (int i = 0; i < satirsayisi; i++) {
          metin=Satirlar[i];//dizinin her satırını metin adındaki bir string değişkene atıyoruz.
          
          for (int j = 0; j < metin.length(); j++) {//metine atılan satırdaki harf kadar for'u döndürüyoruz.
            
             harf=metin.substring(j, j+1);//harf değişkenine metinin o harfinde ne varsa onu atıyoruz.

                if (harf.equals("+")||harf.equals("-")||harf.equals("=")||harf.equals("*")||harf.equals("/")||harf.equals("&")||harf.equals("<")||harf.equals(">")||harf.equals("!")||harf.equals("|")) //+ mı = mi onu kontrol ediyoruz.
                {
                    operatorsayisi++; //operatorsayisi değişkenini 1 arttırıyoruz.
                }
            }
            for (int j = 0; j < metin.length()-1; j++) { //1 sayılacak operatörleri bulmak için bir for oluşturuyoruz.
                 harf2=metin.substring(j, j+2); //harf2 değişkenine 2 li olarak okuduğumuz veriyi atıyoruz.
                 if (harf2.equals("++")||harf2.equals("--")||harf2.equals("&&")||harf2.equals("==")||harf2.equals("!=")||harf2.equals("<=")||harf2.equals(">=")) {
                 operatorsayisi2++;//operatorsayisi2 değişkenini 1 arttırıyoruz.
              }
            }
        }
        System.out.println("Toplam Operatör Sayısı : " + (operatorsayisi-operatorsayisi2)); //Ekrana yazdırma işlemlerini yapıyoruz.
        System.out.println("Toplam Fonksiyon Sayısı : " + fonksiyonsayisi);
        System.out.println("Toplam Parametre Sayısı : " + ParametreSayisi);
        System.out.println("Fonksiyon İsimleri : ");
        for (int i = 0; i < Fonksiyonlar.size(); i++) {
            System.out.print(Fonksiyonlar.get(i)+":");
            System.out.println(Parametreler2.get(i));  
        }     
    }
}
