package com.fly.rotamenu;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fly.rotamenu.util.GlideImageLoader;
import com.fly.rotamenu.widget.CircularFloatingMenu;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.yayandroid.rotatable.Rotatable;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * @author 作者：zhoufeng
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;


    @BindView(R.id.circularFloatingMenu)
    CircularFloatingMenu circularFloatingMenu;
    @BindView(R.id.circularFloatingMenu_small)
    CircularFloatingMenu circularFloatingMenuSmall;

    @BindView(R.id.menu_action_image)
    ImageButton menuActionImage;
    @BindView(R.id.menu_action_image_small)
    ImageButton menuActionImageSmall;

    @BindView(R.id.banner)
    Banner banner;

    @BindView(R.id.home_video_chat_image)
    CircleImageView homeVideoChatImage;
    @BindView(R.id.home_video_chat_image2)
    CircleImageView homeVideoChatImage2;

    private Context mContext;


    private String videoChatImages[] = {
            "https://img8.jikeyue.com/b100/3a/e5/3ae5aef6679497c3a4f9695959194fad.jpg",
            "https://img8.jikeyue.com/b100/d5/53/d5538aa5028819910dded42f518f3a56.jpg",
            "https://img8.jikeyue.com/a100/50/ce/50cec61829c79be1a6ebaddcb30a54c1.jpg",
            "https://img8.jikeyue.com/b100/27/28/2728a830dcfd2961abfd5ef354da9737.jpg",
            "https://img8.jikeyue.com/b100/75/a8/75a831c9ab59f751707f119d0208abde.jpg",
            "https://img8.jikeyue.com/b100/d5/c9/d5c93d6f9d4f402d61829ae8ca00ed3d.jpg",
            "https://img8.jikeyue.com/b100/3d/61/3d6154cc9537b4a4e899e453afe9df94.jpg",
            "https://img8.jikeyue.com/b100/ac/4f/ac4ff0dc99a3861bdcd6fb3ccfb51ef0.jpg",
            "https://img8.jikeyue.com/b100/6c/e1/6ce139eddf518fe8f38de480a30c92b7.jpg",
            "https://img8.jikeyue.com/b100/a0/59/a059f00ace5f9224f190a37116d9c405.jpg",
            "https://img8.jikeyue.com/b100/1c/da/1cda260bd72620ef44c0bd726d04adda.jpg",
            "https://img8.jikeyue.com/b100/5a/78/5a78d3564193be33aa53f0ed6a3cc9a2.jpg",
            "https://img8.jikeyue.com/b100/2b/82/2b8201dbfc13f9ba12df838792136a6f.jpg",
            "https://img8.jikeyue.com/b100/61/5b/615b77d53baa32d347923682aa7bab58.jpg",
            "https://img8.jikeyue.com/a100/28/3c/283c5ed32f900a56b3f2246eba852c10.jpg",
            "https://img8.jikeyue.com/b100/4f/b1/4fb1a5987ac6fc5bc44b313f21544437.jpg",
            "https://img8.jikeyue.com/b100/d0/3c/d03c92647521fa4c811f173160a4b11b.jpg",
            "https://img8.jikeyue.com/b100/61/28/6128e6514006055ab1e584594f59db3d.jpg",
            "https://img8.jikeyue.com/b100/22/c6/22c6dece97a86c762852c6619bc639a3.jpg",
            "https://img8.jikeyue.com/b100/44/22/44220c2acf750316624eccf34ea48fa5.jpg",
            "https://img8.jikeyue.com/b100/1a/16/1a1633bef0a52015659f34887aa2619f.jpg",
            "https://img8.jikeyue.com/b100/be/9c/be9cad07736942324b0d27cafd77afb4.jpg",
            "https://img8.jikeyue.com/b100/44/04/440494f75684259a73f3a4643adb7e92.jpg",
            "https://img8.jikeyue.com/b100/83/15/8315f63a245be9593856e05a13e946ba.jpg",
            "https://img8.jikeyue.com/b100/1e/17/1e17d7829f6ff88bde5698adb050f192.jpg",
            "https://img8.jikeyue.com/b100/32/d8/32d811581183630290d7658b4e11af60.jpg",
            "https://img8.jikeyue.com/b100/22/b7/22b7fc6b25804ee8305716121c9d5858.jpg",
            "https://img8.jikeyue.com/b100/a7/e0/a7e0510867c5a839dad04abfa6d4ebc1.jpg",
            "https://img8.jikeyue.com/b100/69/39/6939d6a33f0bbca52e697b1780d26bde.jpg",
            "https://img8.jikeyue.com/a100/ab/bd/abbdcac5c2c307eff9b371feb5806b4c.jpg",
            "https://img8.jikeyue.com/b100/1a/55/1a550d6bb262d9abb197b2a10170601e.jpg",
            "https://img8.jikeyue.com/b100/ef/81/ef8153e91f628acec842c1ccb67d0673.jpg",
            "https://img8.jikeyue.com/b100/09/6b/096b74f42e1ba26fc90f361611625b8f.jpg",
            "https://img8.jikeyue.com/a100/21/b6/21b6f5c6a77d905c283ea70109e7bd6f.jpg",
            "https://img8.jikeyue.com/a100/bf/8f/bf8f1fcfb75f4055c9c90a2fa320bd12.jpg",
            "https://img8.jikeyue.com/a100/d6/c3/d6c3e87b54a3e1c243afe6cf8a080df1.jpg",
            "https://img8.jikeyue.com/b100/64/79/6479f49374d0f98733ec7cad57e00953.jpg",
            "https://img8.jikeyue.com/b100/c0/3c/c03cf4a1e8574ddec716115f37242208.jpg",
            "https://img8.jikeyue.com/a100/7f/01/7f01e95b24ab13f91e8f941fe1b8c55a.jpg",
            "https://img8.jikeyue.com/a100/1f/a3/1fa314ed17fe1bdd2e1e1340cf2099ff.jpg"

    };

    private boolean isFirst = true;

    private String bannerImages[] = {
            "https://img8.jikeyue.com/b/11/09/1109eb226a1a9f2931d2a25b7ef19e6a.jpg",
            "https://img8.jikeyue.com/b/e1/8d/e18d408859c1bb6ce69aa2a339115914.jpg"

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ButterKnife.bind(this);
        initAnim();
        initBanner();
        initMenu();

        if (videoChatImages != null && videoChatImages.length > 0) {
            handlerImage.postDelayed(runnableChangeImage, allDuration);
            Glide.with(mContext).load(videoChatImages[0]).into(homeVideoChatImage2);
        }
    }

    private void initAnim(){
        mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
        mShowAnimation.setDuration(500);
        mShowAnimation.setFillAfter(true);

        mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
        mHideAnimation.setDuration(500);
        mHideAnimation.setFillAfter(true);
    }


    private void initMenu() {
        circularFloatingMenu.setOnItemClickListener(new CircularFloatingMenu.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int index) {

            }

            @Override
            public void onMenuClick(View view, boolean isOpen) {
                Log.w(TAG, "circularFloatingMenu onMenuClick isOpen:" + isOpen);
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        circularFloatingMenu.setVisibility(View.GONE);
                        circularFloatingMenuSmall.setVisibility(View.VISIBLE);
                        openCircularFloatingMenuSmall();
                    }
                }, 300);
                ViewPropertyAnimator.animate(view).rotation(isOpen ? -135 : 0).setDuration(400).start();
            }
        });


        circularFloatingMenu.setOnItemTranslationListener(new CircularFloatingMenu.OnItemTranslationListener() {
            Interpolator outInterpolator = new OvershootInterpolator();
            Interpolator inInterpolator = new AnticipateInterpolator();
            float defaultRotation = -180;
            float defaultAlpha = 0f;

            @Override
            public void translationItem(View v, int x, int y, boolean isOpen) {
                if (isOpen) {
                    ViewHelper.setRotation(v, defaultRotation);
                    ViewHelper.setAlpha(v, defaultAlpha);
                }
                Interpolator interpolator = isOpen ? outInterpolator : inInterpolator;
                float toRotation = isOpen ? 0 : defaultRotation;
                float toAlpha = isOpen ? 1 : defaultAlpha;

                ViewPropertyAnimator.animate(v).translationX(x).translationY(y).rotation(toRotation).alpha(toAlpha)
                        .setInterpolator(interpolator).setDuration(400).start();
            }
        });

        circularFloatingMenuSmall.setOnItemClickListener(new CircularFloatingMenu.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int index) {

            }

            @Override
            public void onMenuClick(View view, final boolean isOpen) {
                Log.w(TAG, "circularFloatingMenu onMenuClick isOpen:" + isOpen);
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        circularFloatingMenu.setVisibility(View.VISIBLE);
                        circularFloatingMenuSmall.setVisibility(View.GONE);
                        openCircularFloatingMenu();
                    }
                }, 400);
                ViewPropertyAnimator.animate(view).rotation(isOpen ? 135 : 0).setDuration(500).start();
            }
        });


        circularFloatingMenuSmall.setOnItemTranslationListener(new CircularFloatingMenu.OnItemTranslationListener() {
            Interpolator outInterpolator = new OvershootInterpolator();
            Interpolator inInterpolator = new AnticipateInterpolator();
            float defaultRotation = -180;
            float defaultAlpha = 0f;

            @Override
            public void translationItem(View v, int x, int y, boolean isOpen) {
                if (isOpen) {
                    ViewHelper.setRotation(v, defaultRotation);
                    ViewHelper.setAlpha(v, defaultAlpha);
                }
                Interpolator interpolator = isOpen ? outInterpolator : inInterpolator;
                float toRotation = isOpen ? 0 : defaultRotation;
                float toAlpha = isOpen ? 1 : defaultAlpha;
                ViewPropertyAnimator.animate(v).translationX(x).translationY(y).rotation(toRotation).alpha(toAlpha)
                        .setInterpolator(interpolator).setDuration(400).start();
            }
        });

        if (isFirst) {
            isFirst = false;
            openCircularFloatingMenu();
        }
    }


    private void openCircularFloatingMenu() {
        ViewPropertyAnimator.animate(menuActionImage).rotation(135).setDuration(400).start();
        circularFloatingMenu.dealOpen();
        circularFloatingMenu.setmIsOpen(true);
    }

    private void openCircularFloatingMenuSmall() {
        ViewPropertyAnimator.animate(menuActionImageSmall).rotation(-90).setDuration(300).start();
        circularFloatingMenuSmall.dealOpen();
        circularFloatingMenuSmall.setmIsOpen(true);
    }


    private void initBanner() {
        banner.setImages(Arrays.asList(bannerImages))
                .setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .start();
    }


    private Handler handlerImage = new Handler();
    private int imageIndex = -1;
    private final int fzDuration = 100;
    private final int allDuration = 3000;
    private final int fzPageSize = 8;

    private AlphaAnimation mHideAnimation = null;
    private AlphaAnimation mShowAnimation = null;

    private Runnable runnableChangeImage = new Runnable() {
        @Override
        public void run() {
            if (imageIndex == -1) {
                imageIndex++;
                Rotatable rotatable = new Rotatable.Builder(homeVideoChatImage)
                        .direction(Rotatable.ROTATE_BOTH)
                        .build();
                rotatable.setTouchEnable(false);
                rotatable.rotate(Rotatable.ROTATE_Y, 90, fzDuration);
                Rotatable rotatable2 = new Rotatable.Builder(homeVideoChatImage2)
                        .direction(Rotatable.ROTATE_BOTH)
                        .listener(new Rotatable.RotationListener() {
                            @Override
                            public void onRotationChanged(float newRotationX, float newRotationY) {
                                homeVideoChatImage2.setVisibility(View.VISIBLE);
                                homeVideoChatImage.setVisibility(View.GONE);
                                homeVideoChatImage.setAnimation(null);
                                homeVideoChatImage2.setAnimation(null);
                                handlerImage.postDelayed(runnableChangeImage, allDuration - fzDuration * 2);
                            }
                        })
                        .build();
                rotatable2.setTouchEnable(false);
                rotatable2.rotate(Rotatable.ROTATE_Y, 180, fzDuration * 2);
            } else {

                if (imageIndex < videoChatImages.length) {
                    imageIndex++;
                }
                if (imageIndex >= videoChatImages.length || imageIndex < 0) {
                    imageIndex = 0;
                }

                if (imageIndex % fzPageSize == 0) {
                    Rotatable rotatable = new Rotatable.Builder(homeVideoChatImage2)
                            .direction(Rotatable.ROTATE_BOTH)
                            .build();
                    rotatable.setTouchEnable(false);
                    rotatable.rotate(Rotatable.ROTATE_Y, 90, fzDuration);
                    Rotatable rotatable2 = new Rotatable.Builder(homeVideoChatImage)
                            .direction(Rotatable.ROTATE_BOTH)
                            .listener(new Rotatable.RotationListener() {
                                @Override
                                public void onRotationChanged(float newRotationX, float newRotationY) {
                                    homeVideoChatImage.setVisibility(View.VISIBLE);
                                    homeVideoChatImage2.setVisibility(View.GONE);
                                    homeVideoChatImage.setAnimation(null);
                                    homeVideoChatImage2.setAnimation(null);
                                    if (imageIndex < videoChatImages.length) {
                                        Glide.with(mContext).load(videoChatImages[imageIndex + 1]).into(homeVideoChatImage2);
                                    } else {
                                        Glide.with(mContext).load(videoChatImages[0]).into(homeVideoChatImage2);
                                    }
                                    handlerImage.postDelayed(runnableChangeImage, allDuration - fzDuration * 2);
                                }
                            })
                            .build();
                    rotatable2.setTouchEnable(false);
                    rotatable2.rotate(Rotatable.ROTATE_Y, 180, fzDuration * 2);

                } else if (imageIndex % fzPageSize == 1) {
                    Rotatable rotatable = new Rotatable.Builder(homeVideoChatImage)
                            .direction(Rotatable.ROTATE_BOTH)
                            .build();
                    rotatable.setTouchEnable(false);
                    rotatable.rotate(Rotatable.ROTATE_Y, 90, fzDuration);
                    Rotatable rotatable2 = new Rotatable.Builder(homeVideoChatImage2)
                            .direction(Rotatable.ROTATE_BOTH)
                            .listener(new Rotatable.RotationListener() {
                                @Override
                                public void onRotationChanged(float newRotationX, float newRotationY) {
                                    homeVideoChatImage2.setVisibility(View.VISIBLE);
                                    homeVideoChatImage.setVisibility(View.GONE);
                                    homeVideoChatImage.setAnimation(null);
                                    homeVideoChatImage2.setAnimation(null);
                                    handlerImage.postDelayed(runnableChangeImage, allDuration - fzDuration * 2);
                                }
                            })
                            .build();
                    rotatable2.setTouchEnable(false);
                    rotatable2.rotate(Rotatable.ROTATE_Y, 180, fzDuration * 2);

                } else {
                    homeVideoChatImage.setVisibility(View.GONE);
                    homeVideoChatImage2.setVisibility(View.VISIBLE);
                    showAnimationHideForView();
                }

            }

        }
    };

    private void dealImageIndex() {
        if (imageIndex < videoChatImages.length) {
            imageIndex++;
        }
        if (imageIndex >= videoChatImages.length || imageIndex < 0) {
            imageIndex = -1;
        }
    }


    public void showAnimationHideForView() {
        mHideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Glide.with(mContext).load(videoChatImages[imageIndex]).into(homeVideoChatImage2);
                showAnimationShowForView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        homeVideoChatImage2.startAnimation(mHideAnimation);
    }

    public void showAnimationShowForView() {
        mShowAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handlerImage.postDelayed(runnableChangeImage, allDuration - 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        homeVideoChatImage2.startAnimation(mShowAnimation);
    }




    @Override
    protected void onPause() {
        super.onPause();
        handlerImage.removeCallbacks(runnableChangeImage);
    }


}

