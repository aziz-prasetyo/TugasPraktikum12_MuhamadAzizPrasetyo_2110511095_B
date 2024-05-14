package com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.R;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.activities.ListMembersActivity;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.activities.UpdateActivity;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.db.DBHelper;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.model.Member;

import java.io.Serializable;
import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    private ArrayList<Member> listMembers = new ArrayList<>();
    private Activity activity;
    private DBHelper dbHelper;
    public MemberAdapter(Activity activity) {
        this.activity = activity;
        dbHelper = new DBHelper(activity);
    }
    public ArrayList<Member> getListMembers() {
        return listMembers;
    }
    public void setListMembers(ArrayList<Member> listNotes) {
        if (listNotes.size() > 0) {
            this.listMembers.clear();
        }
        this.listMembers.addAll(listNotes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.tvName.setText(listMembers.get(position).getName());
        holder.tvNim.setText(listMembers.get(position).getNim());
        holder.tvEmail.setText(listMembers.get(position).getEmail());
        holder.tvGroupTo.setText(listMembers.get(position).getGroupTo());
        holder.tvAppName.setText(listMembers.get(position).getAppName());
        holder.btnEdit.setOnClickListener((View v) -> {
            Intent intent = new Intent(activity, UpdateActivity.class);
            intent.putExtra("user", (Serializable) listMembers.get(position));
            activity.startActivity(intent);
        });
        holder.btnDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Konfirmasi hapus");
            builder.setMessage("Apakah yakin akan dihapus?");
            builder.setPositiveButton("YA", (dialog, which) -> {
                dbHelper.deleteUser(listMembers.get(position).getId());
                Toast.makeText(activity, "Hapus berhasil!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity, ListMembersActivity.class);
                activity.startActivity(myIntent);
                activity.finish();
            });
            builder.setNegativeButton("TIDAK", (dialog, which) -> dialog.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }
    @Override
    public int getItemCount() {
        return listMembers.size();
    }
    public class MemberViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName, tvNim, tvEmail, tvGroupTo, tvAppName;
        final Button btnEdit, btnDelete;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvNim = itemView.findViewById(R.id.tv_item_nim);
            tvEmail = itemView.findViewById(R.id.tv_item_email);
            tvGroupTo = itemView.findViewById(R.id.tv_item_group_to);
            tvAppName = itemView.findViewById(R.id.tv_item_app_name);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
