package konkuksw.mobileprogramming2019.roadline.global.widget

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import konkuksw.mobileprogramming2019.roadline.databinding.DialogBaseBinding

open class BaseDialog(context: Context) : Dialog(context) {
    val binding: DialogBaseBinding by lazy{
        DialogBaseBinding.inflate(LayoutInflater.from(context))
    }
    open class Builder(private val mContext: Context) {
        open val dialog = BaseDialog(mContext)
        open fun create(): Builder {
            dialog.create()
            dialog.setContentView(dialog.binding.root)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            var size = Point()
            dialog.window!!.windowManager.defaultDisplay.getSize(size)
            dialog.window!!.setLayout((size.x * 0.872f).toInt(), size.y)
            return this
        }
        

        fun setTitle(text: String): Builder {
            dialog.binding.okText = text
            return this
        }

        fun setMessage(text: String): Builder {
            dialog.binding.message = SpannableStringBuilder(text)
            dialog.binding.tvDialogText.visibility = View.VISIBLE
            return this
        }
        fun setMessage(text: SpannableStringBuilder): Builder {
            dialog.binding.message = text
            dialog.binding.tvDialogText.visibility = View.VISIBLE
            return this
        }
        fun setGuideMessage(text: String): Builder {
            dialog.binding.guideMessage = SpannableStringBuilder(text)
            dialog.binding.tvDialogTextGuide.visibility = View.VISIBLE
            return this
        }

        open fun setCancelButton(text: String="취소", onClick: View.OnClickListener? = null): Builder {
            dialog.binding.cancelText = text
            if(onClick == null)
                dialog.binding.btnCancel.setOnClickListener { dialog.dismiss() }
            else
                dialog.binding.btnCancel.setOnClickListener(onClick)
            dialog.binding.btnCancel.visibility = View.VISIBLE
            dialog.binding.dialogLinearLayout.visibility = View.VISIBLE
            return this
        }

        open fun setOkButton(text: String="확인", onClick: View.OnClickListener): Builder {
            dialog.binding.okText = text
            dialog.binding.btnOk.setOnClickListener(onClick)
            dialog.binding.btnOk.visibility = View.VISIBLE
            dialog.binding.dialogLinearLayout.visibility = View.VISIBLE
            return this
        }
        fun setDismissListener(listener: DialogInterface.OnDismissListener):Builder {
            dialog.setOnDismissListener(listener)
            return this
        }

        fun dismissDialog() {
            dialog.dismiss()
        }
        open fun show(): BaseDialog {
            dialog.show()
            return dialog
        }
    }
}