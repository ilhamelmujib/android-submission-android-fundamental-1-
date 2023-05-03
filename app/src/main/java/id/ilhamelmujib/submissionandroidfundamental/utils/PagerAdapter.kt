package id.ilhamelmujib.submissionandroidfundamental.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity: AppCompatActivity, private val fragments: List<Fragment>) :
    FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}