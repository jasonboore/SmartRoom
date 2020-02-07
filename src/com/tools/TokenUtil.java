package com.tools;

/*
 * 
 * genToken() �������token 
 * 
 * verificationToken(String token) ���ܲ����жϸ�token״̬
 * 
 * */
import java.util.Random;
import java.util.concurrent.TimeUnit;



import org.apache.commons.lang.StringUtils;


public class TokenUtil {

    private static final String[] codeBase= {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    private static Random rand= new Random();

    /** XXTEA���ܽ��ܵ���Կ */
    private static String secKey = "mq";

    /** token��ʱ����(��) */
    private static long expire = 30;


    /** ��֤���ַ��� */
    private static int charCount = 4;

    public static final  String  genToken() {
        StringBuffer sb= new StringBuffer();
        for(int i=0; i<charCount; i++){
            int randInt= Math.abs(rand.nextInt());
            sb.append(codeBase[randInt % codeBase.length]);
        }
        long timestamp= System.currentTimeMillis();
        String token= null;
        
        System.out.println("��Կ:  "+secKey);
        System.out.println("ǰ׺:  "+sb.toString());//ǰ׺
        System.out.println("ʱ���:  "+timestamp);//ʱ���
        
        token= String.format("%s_%d", sb.toString(), timestamp);
        System.out.println("δ���ܵ�token: "+token);
        
        token= XXTEAUtil.encrypt(token, secKey);
        System.out.println("���ܵ�token: "+token);
        return token;
    }

    public static String verificationToken(String token)   {
    	//ԭʼ
    	System.out.println("�������Ҫ������token:"+token);
   
    	//����
        String plainText= XXTEAUtil.decrypt(token,secKey);        
        System.out.println("���ܵ�token: "+plainText);
  
        
        	if (StringUtils.isBlank(plainText)){                
                System.out.println("����ʧ��,token�����⵽�۸�");
                return "����ʧ��";
            }
        
        	
            String[] plainTextArr= plainText.split("_");
            if (plainTextArr.length!=2){             
            	System.out.println("token���ݸ�ʽ����");
            	 return "���ݸ�ʽ����";
            }
         //   System.out.println("���ݸ�ʽ: "+plainTextArr);
            
            
            long timestamp= 0;
            try{
                timestamp= Long.parseLong(plainTextArr[1]);
            }catch(NumberFormatException e){                
            	 System.out.println("ʱ�����Ч");
            	 return "ʱ�����Ч";
            }
            
            
            if ((System.currentTimeMillis() - timestamp)>TimeUnit.MILLISECONDS.convert(expire+5, TimeUnit.DAYS)){                
                System.out.println("token�ѹ���");
                return "token�ѹ���";
            }
            return "�ɹ�";
    }
}
