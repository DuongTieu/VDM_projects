package duong.tieu.vdmproject.notifications;

import android.content.Context;

import com.google.gson.Gson;

import duong.tieu.vdmproject.activities.NotNow;
import duong.tieu.vdmproject.models.DGetProject;
import duong.tieu.vdmproject.models.DMessages;
import duong.tieu.vdmproject.models.DNotification;
import duong.tieu.vdmproject.models.Models;

/**
 * Created by duong on 07/04/2016.
 */
public class NotificationListener {

    private String mJson;
    private int mType;
    private Context mContext;

    public NotificationListener() {
    }

    public NotificationListener(Context mContext) {
        this.mContext = mContext;
    }

    public DMessages parseJsonMessage(String mJson) {
        Gson gson = new Gson();
        return gson.fromJson(mJson, DMessages.class);
    }

    public DGetProject parseJsonDGetProject(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, DGetProject.class);
    }

    public void analyze(String string) {
        Gson gson = new Gson();
        DNotification parseJson = gson.fromJson(string, DNotification.class);


        int page = 0;
        int id  = 0;
        String title =  parseJson.getTitle();
//        String title = parseJson.getUser_name() + " " + parseJson.getTitle();
        String content = parseJson.getContent();

        int n = 0;
        try {
            n = Integer.parseInt(parseJson.getType());
            page  = Integer.parseInt(parseJson.getPage());
//            id = Integer.parseInt(parseJson.getId());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Lỗi type trong json");
        }

        switch (n){
            case 1:
                analyzeMessage(content,title ,mJson);
                break;
            case 2: analyzeRalatedProject(page, title, content);
                break;
            case 3: analyzeCareProject(page, title, content);
                break;
            case 4: analyzeSuppot(content, title, mJson );
                break;

        }
    }

    private void analyzeCareProject(int page, String title, String content) {
        switch (page){
            case 1:
                System.out.println("có 1 dự án quan tâm mới");
                new Notifications(mContext,
                        NotNow.class,
                        title,
                        " " + content,
                        mJson,
                        Models.CARE_PROJECT_METHOD,
                        Models.NEW_PROJECT_TYPE
                ).addNotification();
                break;
            case 2:
                System.out.println("thích dự án bạn quan tâm mới");
                new Notifications(mContext,
                        NotNow.class,
                        title,
                        " " + content,
                        mJson,
                        Models.CARE_PROJECT_METHOD,
                        Models.NEW_LIKE_TYPE
                ).addNotification();
                break;
            case 3:
                System.out.println("Comment dụ án bạn quan tâm");
                new Notifications(mContext,
                        NotNow.class,
                        title,
                        " " + content,
                        mJson,
                        Models.CARE_PROJECT_METHOD,
                        Models.NEW_COMMENT_TYPE
                ).addNotification();
                break;
            case 4:
                System.out.println("quan tâm dụ án bạn quan tâm");
                new Notifications(mContext,
                        NotNow.class,
                        title,
                        " " + content,
                        mJson,
                        Models.CARE_PROJECT_METHOD,
                        Models.NEW_CARE_TYPE
                ).addNotification();

                break;
        }

    }

    private void analyzeRalatedProject(int page, String title, String content) {
        switch (page){
            case 1:
                    System.out.println("có 1 dự án liên quan mới");
                    new Notifications(mContext,
                            NotNow.class,
                            title,
                            " " + content,
                            mJson,
                            Models.RELATED_PROJECT_METHOD,
                            Models.NEW_PROJECT_TYPE
                    ).addNotification();
                break;
            case 2:
                System.out.println("Lượt thích dự án");
                new Notifications(mContext,
                        NotNow.class,
                        title,
                        " " + content,
                        mJson,
                        Models.RELATED_PROJECT_METHOD,
                        Models.NEW_LIKE_TYPE
                ).addNotification();
                break;
            case 3:
                System.out.println("Lượt quan tam dự án");
                new Notifications(mContext,
                        NotNow.class,
                        title,
                        " " + content,
                        mJson,
                        Models.RELATED_PROJECT_METHOD,
                        Models.NEW_CARE_TYPE
                ).addNotification();
                break;
            case 4:
                System.out.println("Lượt comment dự án");
                new Notifications(mContext,
                        NotNow.class,
                        title,
                        " " + content,
                        mJson,
                        Models.RELATED_PROJECT_METHOD,
                        Models.NEW_COMMENT_TYPE
                ).addNotification();
                break;
        }
    }

    private void analyzeSuppot(String content, String title, String mJson) {
        System.out.println("có 1 yêu cầu hỗ trợ");
        new Notifications(mContext,
                NotNow.class,
                title,
                " " + content,
                mJson,
                Models.SUPPORT_METHOD
        ).addNotification();
    }



    private void analyzeMessage(String content,String title, String mJson) {
        System.out.println("có 1 tin nhắn mới");
        new Notifications(mContext,
                NotNow.class,
                title,
                " " + content,
                mJson,
                Models.SEND_MESSAGE_METHOD
        ).addNotification();

    }




}
