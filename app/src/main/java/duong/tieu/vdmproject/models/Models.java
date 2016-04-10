package duong.tieu.vdmproject.models;

/**
 * Created by Nhahv on 4/4/2016.
 */
public class Models {

    public static final String DATABASE_NAME = "VSI.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTENTS = "tbl_contents";
    public static final String ID = "id";
    public static final String CATE_ID = "cate_id";
    public static final String CAR_ID = "car_id";
    public static final String CODE = "code";
    public static final String TITLE = "title";
    public static final String INTOR = "intro";
    public static final String FULL_TEXT = "fulltext";
    public static final String THUMB = "thumb";
    public static final String AUTHO = "author";
    public static final String VIEW = "view";
    public static final String IS_HOT = "ishot";
    public static final String C_DATE = "cdate";
    public static final String M_DATE = "mdate";
    public static final String META_TITLE = "meta_title";
    public static final String META_KEY = "meta_key";
    public static final String IS_ACTIVE_CONTENTS = "isactive";

    public static final String TABLE_MEMBER = "tbl_member";
    public static final String MEM_ID = "mem_id";
    public static final String USER_NAME = "username";
    public static final String PASS_WORD = "password";
    public static final String FIRST_NAME = "firsname";
    public static final String LAST_NAME = "lastname";
    public static final String BIRTH_DAY = "birthday";
    public static final String GENDER = "gender";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    public static final String MOBILE = "mobile";
    public static final String EMAIL = "email";
    public static final String JOIN_DATE = "joindate";
    public static final String LAST_LOGIN = "lastlogin";
    public static final String GMEN_ID = "gmen_id";
    public static final String IS_ACTIVE_MEMBER = "isactive";

    public static final String TABLE_PROFILE = "tbl_member_profile";
    public static final String ID_PROFILE_ = "id";
    public static final String USER_NAME_PROFILE = "username";
    public static final String FIRST_NAME_PROFILE = "first_name";
    public static final String LAST_NAME_PROFILE = "last_name";
    public static final String BIRTHDAY_PROFILE = "birthday";
    public static final String GENDER_PROFILE = "gender";
    public static final String AVATA_PROFILE = "avata";
    public static final String ADDRESS_PROFILE = "address";
    public static final String CITY_PROFILE = "city";
    public static final String COUNTRY_PROFILE = "country";
    public static final String TEL_PROFILE = "tel";
    public static final String EMAIL_PROFILE = "email";
    public static final String FACEBOOK_PROFILE = "facebook";
    public static final String TWITTER = "twitter";


    public static final String COLUMN_1 = "";

    public static final String TABLE_MESSAGE = "tbl_mesager";
    public static final String ID_MESSAGER = "id";
    public static final String FROM_MESSAGER = "from";
    public static final String TO_MESSAGER = "to";
    public static final String CONTENT_MESSAGER = "tbl_mesager";
    public static final String CDATE_MESSAGER = "cdate";
    public static final String ISRUN_MESAGER = "isrun";

    public static final String URL = "http://vsi.vietitech.com/api/member/mem_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=";
    public static final String IS_LOGIN = "isLogin";
    public static final String LOGIN = "login";
    public static final String GET_USER = "getUser";

    public static final String URL_GET_USER = "http://vsi.vietitech.com/api/mem_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getUser&username=admin";
    public static final String URL_GET_PROJECT =
            "http://vsi.vietitech.com/api/project_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getProject&username=";
    public static final String URL_GET_PROJECT_CARE =
            "http://vsi.vietitech.com/api/project_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getProjectCare&username=";
    public static final String URL_GET_PROJECT_ITEM =
            "http://vsi.vietitech.com/api/project_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getProjectItme&username=";
    public static final String URL_GET_NEWS_VSI_VIP =
            "http://vsi.vietitech.com/api/news_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getNewsVip&username=";
    // tao bang co hoi
    public static final String TABLE_PROJECT = "tbl_projects";
    public static final String ID_PROJECT = "id";
    public static final String CAR_ID_PROJECT = "car_id";
    public static final String CODE_PROJECT = "code";
    public static final String TITLE_PROJECT = "title";
    public static final String INTRO_PROJECT = "intro";
    public static final String CONTENT_PROJECT = "content";
    public static final String FILES_PROJECT = "files";
    public static final String CDATE_PROJECT = "cdate";
    public static final String FROM_DATE_PROJECT = "from_date";
    public static final String END_DATE_PROJECT = "end_date";
    public static final String USERNAME_PROJECT = "username";
    public static final String C_COMMENT_PROJECT = "c_comment";
    public static final String LIKE_PROJECT = "like";
    public static final String UNLIKE_PROJECT = "unlike";
    public static final String ISACTIVE_PROJECT = "isactive";
    public static final int MESSAGES_SEND = 0;
    public static final int MESSAGE_RECEIVE = 1;

    public static final int SEND_MESSAGE_METHOD = 1;
    public static final int RELATED_PROJECT_METHOD = 2;
    public static final int CARE_PROJECT_METHOD = 3;
    public static final int SUPPORT_METHOD = 4;
    public static final int NEW_PROJECT_TYPE = 1;
    public static final int NEW_SUPPORT_TYPE = 5;
    public static final int NEW_LIKE_TYPE = 2;
    public static final int NEW_COMMENT_TYPE = 3;
    public static final int NEW_CARE_TYPE = 4;

    public static final String VERIFY = "VERIFY";
    public static final int KEYLOGIN = 1;

    public static String URL_NEW_VSI =
            "http://vsi.vietitech.com/api/news_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getNewsVsi&username=";

    public static final String PUBLIC_KEY = "pub-c-470b0c62-1d29-4905-ad3d-10e41ecae909";
    public static final String SUBSCRIBE_KEY = "sub-c-047ca2dc-fbc7-11e5-861b-02ee2ddab7fe";
    public static final String SECRET_KEY = "sec-c-NmIyOTA3NTMtYTY1Yi00Nzc2LWI1MmItOGQ2MjA0OGNkZjEy";
    public static final String CHANNEL_ = "vsi_private_chat_";
    public static final String CHANNEL_NOTIFICATION = "vsi_group_chanel_notification";
    public static final String DATA = "DATA";
    public static final String PACKAGE = "PACKAGE";
    public static final String PROJECT_ID = "project_id";
    public static final String URL_GET_SUPPORT =
            "http://vsi.vietitech.com/api/support_api.php?publicKey=5628acfce494c53189505f337bfa6870&action=getSupport";
}
