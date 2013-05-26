package api;

import java.net.URLEncoder;


public class VKAuth {
	
	private static String redirect_url = "http://oauth.vk.com/blank.html";;
	
    public static String getUrl(String api_id, String settings){
        String url="http://oauth.vk.com/authorize?client_id="+api_id+"&display=page&scope="+
        		settings+"&redirect_uri="+URLEncoder.encode(redirect_url)+"&response_type=token";
        return url;
    }
	
    public static String getSettings(){
        //http://vk.com/developers.php?oid=-1&p=%D0%9F%D1%80%D0%B0%D0%B2%D0%B0_%D0%B4%D0%BE%D1%81%D1%82%D1%83%D0%BF%D0%B0_%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9
        //+1      Пользователь разрешил отправлять ему уведомления.
        //+2      Доступ к друзьям.
        //+4      Доступ к фотографиям.
        //+8      Доступ к аудиозаписям.
        //+16     Доступ к видеозаписям.
        //+32     Доступ к предложениям.
        //+64     Доступ к вопросам.
        //+128    Доступ к wiki-страницам.
        //+256    Добавление ссылки на приложение в меню слева.
        //+512    Добавление ссылки на приложение для быстрой публикации на стенах пользователей.
        //+1024   Доступ к статусам пользователя.
        //+2048   Доступ заметкам пользователя.
        //+4096   (для Desktop-приложений) Доступ к расширенным методам работы с сообщениями.
        //+8192   Доступ к обычным и расширенным методам работы со стеной.
        //+65536  offline
        //+131072 Доступ к документам пользователя.
        //+262144 Доступ к группам пользователя.
        int settings=1+2+4+8+16+32+64+128+1024+2048+4096+8192+65536+131072+262144;
        //не хватает права notifications
        return Integer.toString(settings);
        //return "friends,photos,audio,video,docs,notes,pages,wall,groups,messages,offline";
    }
    
    public static String[] parseRedirectUrl(String url) throws Exception {
    	return new String[]{};
    }
}
