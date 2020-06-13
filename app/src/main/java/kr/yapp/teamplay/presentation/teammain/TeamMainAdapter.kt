package kr.yapp.teamplay.presentation.teammain

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.R
import kr.yapp.teamplay.data.teammain.response.TeamMainFeedItemResponse
import kr.yapp.teamplay.databinding.RvItemTeamMainNoticeTypeBinding
import kr.yapp.teamplay.databinding.RvItemTeamMainResultTypeBinding
import kr.yapp.teamplay.domain.entity.teammain.NoticeItem
import kr.yapp.teamplay.domain.entity.teammain.ResultItem
import kr.yapp.teamplay.domain.entity.teammain.TeamMainItemType

class TeamMainAdapter(
    private var list: MutableList<TeamMainFeedItemResponse>,
    val onClickNotice: () -> Unit = {},
    val onClickResult: (isWin: Boolean?, teamName: String?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val type = TeamMainItemType.values().get(viewType)
        return when (type) {
            TeamMainItemType.NOTICE -> {
                NoticeTypeViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.rv_item_team_main_notice_type,
                        parent,
                        false
                    )
                )
            }
            TeamMainItemType.MATCH_RESULT -> {
                ResultTypeViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.rv_item_team_main_result_type,
                        parent,
                        false
                    )
                )
            }
        }
    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = TeamMainItemType.values().get(list[position].type)
        when (type) {
            TeamMainItemType.NOTICE -> (holder as NoticeTypeViewHolder).bindTo(list[position].noticeItem)
            TeamMainItemType.MATCH_RESULT -> (holder as ResultTypeViewHolder).bindTo(list[position].resultItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    fun updateTeamMain(updateList: List<TeamMainFeedItemResponse>) {
        list.clear()
        list.addAll(updateList)
        notifyDataSetChanged()
    }

    inner class ResultTypeViewHolder(
        private val binding: RvItemTeamMainResultTypeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(resultItem: ResultItem?) {
            binding.resultItem = resultItem
            if (binding.resultItem!!.isWin) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.itemResultTypeMatchResultIvWin.visibility = View.VISIBLE
                    binding.itemResultTypeMatchResultIvLose.visibility = View.INVISIBLE
                    binding.resultContent = Html.fromHtml(
                        String.format(
                            "<b>%s</b>과의 경기에서 승리하셨습니다.",
                            resultItem?.teamName
                        ), Html.FROM_HTML_MODE_COMPACT
                    )
                }
                //binding.result = "WIN"
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.itemResultTypeMatchResultIvLose.visibility = View.VISIBLE
                    binding.itemResultTypeMatchResultIvWin.visibility = View.INVISIBLE
                    binding.resultContent = Html.fromHtml(
                        String.format(
                            "<b>%s</b>과의 경기에서 패배하셨습니다.",
                            resultItem?.teamName
                        ), Html.FROM_HTML_MODE_COMPACT
                    )
                }
                //binding.result = "LOSE"
            }
            binding.root.setOnClickListener { onClickResult(resultItem?.isWin, resultItem?.teamName) }
        } // end of bindTo()
    }

    inner class NoticeTypeViewHolder(
        private val binding: RvItemTeamMainNoticeTypeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(noticeItem: NoticeItem?) {
            binding.noticeItem = noticeItem
            binding.root.setOnLongClickListener { view ->
                onClickNotice()
                true
            }
        }
    }


}
