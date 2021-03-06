package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fastrackm.nanodegree.udacity.abhis.yumrecipes.R;
import models.Recipe;

/**
 * Created by abhis on 8/17/2017.
 */

public class RecipeGridAdapter extends RecyclerView.Adapter<RecipeGridAdapter.RecyclerViewHolder>
{
    private ArrayList<Recipe> mRecipeList;
    private Context mContext;
    private RecipeGridAdapterClickHandler mClickHandler;

    public interface RecipeGridAdapterClickHandler
    {
        void onClick(Recipe currentRecipe);
    }

    public RecipeGridAdapter()
    {

    }

    public RecipeGridAdapter(RecipeGridAdapterClickHandler clickHandler)
    {
        mClickHandler = clickHandler;
    }

    @Override
    public RecipeGridAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context currentContext = parent.getContext();
        int layoutID = R.layout.recipe_grid;

        LayoutInflater inflater = LayoutInflater.from(currentContext);

        View view = inflater.inflate(layoutID, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeGridAdapter.RecyclerViewHolder holder, int position)
    {
        holder.mText.setText(mRecipeList.get(position).getName());
        Log.d("RecipeGridAdapter", "does it get here?");

        Log.d("RecipeGridAdapter", mRecipeList.get(position).getName());


    }

    @Override
    public int getItemCount()
    {
        return mRecipeList != null ? mRecipeList.size():0;
    }

    public void setRecipeListData(ArrayList<Recipe> recipeArrayList, Context context)
    {
        mRecipeList = recipeArrayList;
        mContext = context;
        notifyDataSetChanged();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView mText;

        public RecyclerViewHolder(View itemView)
        {
            super(itemView);

            mText = itemView.findViewById(R.id.recipe_name);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view)
        {
            Log.d("RecipeGridAdapter", "does it get here?");
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(mRecipeList.get(adapterPosition));
        }
    }
}
