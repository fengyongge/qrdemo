package app.shaomiao;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

import app.shaomiao.utils.StringUtils;

public class MakeCodeActivity extends AppCompatActivity {


    private final static int QR_WIDTH = 300, QR_HEIGHT = 300;
    Bitmap bitmap1;
    private ImageView iv_code;
    private EditText et_name;
    private Button bt_make;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_code);
        iv_code = (ImageView) findViewById(R.id.iv_code);
        et_name = (EditText) findViewById(R.id.et_name);
        bt_make = (Button) findViewById(R.id.bt_make);
        et_name.setText("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1463989015&di=a09bb7141568b9919897b073682541e6&src=http://a0.att.hudong.com/70/80/01300000208920122217801646936.jpg");
        bt_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StringUtils.isNotEmpty(et_name.getText())){

                    createImage(iv_code,et_name.getText().toString().trim());

                }else{

                    Toast.makeText(MakeCodeActivity.this,"内容不能为空",Toast.LENGTH_LONG).show();
                }
            }
        });
    }





    private Bitmap createImage(ImageView qr_img,String s) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            String text = s;
            if (text == null || "".equals(text) || text.length() < 1) {
                //return;
            }
            BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,QR_WIDTH, QR_HEIGHT);

            System.out.println("w:" + martix.getWidth() + "h:"
                    + martix.getHeight());

            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }

                }
            }
            bitmap1 = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
                    Bitmap.Config.ARGB_8888);
            bitmap1.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            qr_img.setImageBitmap(bitmap1);
            return bitmap1;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap1;
    }





}
