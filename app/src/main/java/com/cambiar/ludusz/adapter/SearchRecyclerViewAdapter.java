package com.cambiar.ludusz.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.model.SearchBase;
import com.cambiar.ludusz.util.AndroidUtil;
import com.cambiar.ludusz.util.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by vibes on 28/4/16.
 */
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {
    private static final String TAG = SearchRecyclerViewAdapter.class.getSimpleName();
    List<SearchBase> searchDataList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public SearchRecyclerViewAdapter(Context context, List<SearchBase> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.searchDataList = data;
    }

    public void delete(int position) {
        searchDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(SearchBase blog) {
        searchDataList.add(blog);
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_search_coach, parent, false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        SearchBase sb = searchDataList.get(position);

        viewHolder.tv_header.setText(sb.getSearchResultName());
        viewHolder.tv_address.setText(sb.getSearchResultAddress());
        viewHolder.tv_coachingOffered.setText("");
        viewHolder.tv_coachingOffered.setText("");
        viewHolder.tv_offerCoachingTo.setText("");
        viewHolder.tv_fee.setText(sb.getSearchResultFee() + "");
        viewHolder.tv_experience.setText("");
        viewHolder.tv_connected_people.setText(sb.getGetSearchResultConnectedPeopleCount() + "");
        viewHolder.tv_like_count.setText(sb.getSearchResultLikes() + "");
        viewHolder.tv_rating_star.setText(AndroidUtil.getIntToStar(Math.round(sb.getSearchResultRatingStar())));

        viewHolder.img_btn_call.setTag("tel:" + sb.getSearchResultContactNumber());
        Picasso.with(context).load(sb.getSearchResultProfilePicture()).placeholder(R.drawable.ic_people).into(viewHolder.iv_profilePic);
    }

    @Override
    public int getItemCount() {
        return searchDataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_header,
                tv_address,
                tv_coachingOffered,
                tv_offerCoachingTo,
                tv_fee,
                tv_experience,
                tv_like_count,
                tv_connected_people,
                tv_review_count,
                tv_rating_star,
                tv_gallery_header;

        ImageButton img_btn_call, img_btn_mail;
        Button btn_book, btn_compare, btn_galary_show_more;

        ImageView iv_profilePic, iv_gal_pic_1, iv_gal_pic_2, iv_gal_pic_3;


        public MyViewHolder(View v) {
            super(v);

            tv_header = (TextView) v.findViewById(R.id.tv_header);
            tv_address = (TextView) v.findViewById(R.id.tv_address);
            tv_coachingOffered = (TextView) v.findViewById(R.id.tv_coaching_offered);
            tv_offerCoachingTo = (TextView) v.findViewById(R.id.tv_offer_coaching_to);
            tv_fee = (TextView) v.findViewById(R.id.tv_fee);
            tv_experience = (TextView) v.findViewById(R.id.tv_experiance);
            tv_like_count = (TextView) v.findViewById(R.id.tv_like_count);
            tv_connected_people = (TextView) v.findViewById(R.id.tv_connected_people_count_right_top);
            tv_review_count = (TextView) v.findViewById(R.id.tv_review_count);
            tv_gallery_header = (TextView) v.findViewById(R.id.tv_galary_header);
            tv_rating_star = (TextView) v.findViewById(R.id.tv_rating_star);

            img_btn_call = (AppCompatImageButton) v.findViewById(R.id.btn_call);
            img_btn_mail = (AppCompatImageButton) v.findViewById(R.id.btn_mail);

            btn_book = (AppCompatButton) v.findViewById(R.id.btn_book);
            btn_compare = (AppCompatButton) v.findViewById(R.id.btn_compare);
            btn_galary_show_more = (AppCompatButton) v.findViewById(R.id.btn_galary_show_more);

            iv_profilePic = (RoundedImageView) v.findViewById(R.id.iv_coach_profile_pic);
            iv_gal_pic_1 = (ImageView) v.findViewById(R.id.iv_galary_1);
            iv_gal_pic_2 = (ImageView) v.findViewById(R.id.iv_galary_2);
            iv_gal_pic_3 = (ImageView) v.findViewById(R.id.iv_galary_3);

            img_btn_call.setOnClickListener(SearchRecyclerViewAdapter.this);
            v.setOnClickListener(SearchRecyclerViewAdapter.this);
        }

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call: {
                 /* if (PermissionChecker.lacksPermissions(context, Manifest.permission.CALL_PHONE)) {
                    ActivityCompat.requestPermissions((SearchActivity) context, new String[]{Manifest.permission.CALL_PHONE}, LuduszConstants.Field.ACTION_CALL_REQUEST_CODE);
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(v.findViewById(R.id.btn_call).getTag().toString()));
                    context.startActivity(intent);
                    return;
                }*/
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(v.findViewById(R.id.btn_call).getTag().toString()));
                context.startActivity(intent);

                break;
            }
            case R.id.btn_mail: {
                Intent intent = new Intent(Intent.EXTRA_EMAIL);
                intent.setData(Uri.parse(v.findViewById(R.id.btn_call).getTag().toString()));
                context.startActivity(intent);
                break;
            }

            default:
                break;

        }
    }

    public void callNumber(Activity context, String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(number));
        //context.startActivity(intent);
    }


}
