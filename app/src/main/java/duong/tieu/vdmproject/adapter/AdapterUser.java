package duong.tieu.vdmproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import duong.tieu.vdmproject.R;
import duong.tieu.vdmproject.models.DGetUser;

/**
 * Created by Nhahv on 4/8/2016.
 */
public class AdapterUser extends ArrayAdapter<DGetUser> {

    private int mLayout;
    private LayoutInflater mInflater;
    private ArrayList<DGetUser> mListUser;

    public AdapterUser(Context context, int resource, ArrayList<DGetUser> mListUser) {
        super(context, resource, mListUser);

        this.mInflater = LayoutInflater.from(context);
        this.mLayout = resource;
        this.mListUser = mListUser;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(mLayout, null);
            viewHolder.mImgView = (ImageView) convertView.findViewById(R.id.imgUser);
            viewHolder.mTvView = (TextView) convertView.findViewById(R.id.tvUser);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DGetUser dGetUser = mListUser.get(position);

//        Bitmap bitmap = downloadImages(dGetUser.getAvatar());
//        Bitmap bitmap1 = getResizedBitmap(bitmap, 50, 50);

        viewHolder.mImgView.setImageResource(R.drawable.ic_avatar);
        viewHolder.mTvView.setText(dGetUser.getUsername());

        return convertView;
    }

    private Bitmap downloadImages(String imageUrl) {

        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);

            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            httpConn.connect();
            int resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConn.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }

    private class ViewHolder {
        private ImageView mImgView;
        private TextView mTvView;
    }
}
