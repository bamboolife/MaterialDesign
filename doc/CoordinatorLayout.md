## CoordinatorLayout知识梳理

### CoordinatorLayout说明
- 1.CoordinatorLayout是一个超级的FrameLayout；
- 2.CoordinatorLayout两个主要的用法意图如下：
  - a.作为一个顶级布局；
  - b.作为一个容器，用于一个或多个子View的特殊交互；
- 3.在同一个父容器内，通过给一个CoordinatorLayout的子views指定CoordinatorLayout.Behavior的behaviors，能够实现出不同的交互效果，那些子Views能够与另一个view相互作用；当使用{@link CoordinatorLayout.DefaultBehavior DefaultBehavior} 注释作为CoordinatorLayout孩子时，view类能够指定一个默认的行为；
- 4.Behaviors通常被用来实现各种各样的交互和来自滑动抽屉、滑动删除元素和按钮关联其他元素产生的额外的布局修改；
- 5.CoordinatorLayout的子view也许会有个anchor（锚点，即view显示在哪块区域）；该视图id必须于CoordinatorLayout的任意后代的id保持一致，但它可能不是固定的孩子本身或固定孩子的后代。 这可以用于相对于其他任意内容长方格放置浮动视图；
- 6.孩子们可以指定{@link CoordinatorLayout.LayoutParams＃insetEdge}来描述视图怎样插入了CoordinatorLayout。 任意的设置躲避相同插入边的子View可通过{@link CoordinatorLayout.LayoutParams＃dodgeInsetEdges}将被适当地移动，以使视图不重叠。

### Behavior
- 1.CoordinatorLayout子view的交互行为插件；
- 2.行为实现用户可以对子视图执行一个或多个交互。这些交互可以包括拖动，滑动，轮流或任何其他手势;

###  AppBarLayout说明
- 1.AppBarLayout是一个垂直的LinearLayout，这个垂直的线性布局实现了许多MaterialDesigns的设计风格概念，也就是说主要应用在滚动的手势操作上；
- 2.子View通过设置LayoutParams#setScrollFlags(int)来表达他们期望的滚动行为方式，在xml中设置方式为：app:layout_scrollFlags；
- 3.AppBarLayout只有用作CoordinatorLayout的直接子类才有效果；如果使用的AppBarLayout在一个不同的ViewGroup中，它的功能很可能不能使用；
- 4.AppBarLayout还需要一个单独的滚动成员，才能知道自己何时滚动；也就是说：你需要设置你的Scrolling view的behavior（app:layout_behavior）为AppBarLayout.ScrollingViewBehavior来通知AppBarLayout什么时候滚动。

layout_scrollFlags的属性说明：

| 属性 | 说明 |
|:----- |:---------- |
| SCROLL_FLAG_SCROLL | 对应xml布局中的scroll，如果要设置其他的滚动flag，这个flag必须要设置，否则无效;collapse时设置该flag的view先全部折叠，expand的时等NestedScrollView滑动到顶部设置该flag的view才会出现。|
| SCROLL_FLAG_EXIT_UNTIL_COLLAPSED |  对应xml布局中的exitUntilCollapsed，设置该flag的view在向上滑动退出屏幕时，不会完全退出，会保留collapsed height（minHeight）高度,测试时是Toolbar的高度；expand时先让NestedScrollView滑动到顶端，才会使剩余的进入屏幕；  |
| SCROLL_FLAG_ENTER_ALWAYS | 对应xml布局中的enterAlways，只要手指向下滑，设置该flag的view就会直接进入屏幕，不管NestedScrollView是否在滚动。collpase的时候，设置该flag的view先折叠完毕后，NestedScrollView才开始滑动； |
| SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED |   对应xml布局中的enterAlwaysCollapsed，是enterAlways的附加flag，使设置该flag的view在进入屏幕时最初只滑动显示到它的collapsed height（minHeight），一旦NestedScrollView滑到顶部，该view再滑动显示剩余的部分。collapsing时先折叠完毕才能使NestedScrollView滚动；单独使用时与只设置scroll flag一样，一般与enter_always一起使用   |
| SCROLL_FLAG_SNAP | 对应xml布局中的snap，设置该flag的view在滚动停止时，如果没有完全显示，会自动滚到到最近的一个边界（顶端、中线和下线）;一般与上面的几种情况一起使用，有阻尼的效果；|

### CollpasingToolbarLayout说明

#### CollapsingToolbarLayout是一个实现了折叠效果的Toolbar的包装；它被设计用来作为AppBarLayout的直接子类来使用；

#### CollpasingToolbarLayout包含一下特点：

- 1.CollapsingTitle-折叠的title：在CollapsingToolbarLayout全部展开时，title的显示的大小会比折叠在一起大些，title会随着屏幕的滚动变小；可以通过CollapsingToolbarLayout中的setTitle（）方法改变title的显示内容；title的外观（颜色，大小）可以通过此类对象的collapsedTextAppearance属性改变折叠时的外观，通过expandedTextAppearance属性改变展开时的外观；
- 2.Content scrim-折叠后的背景：CollapsingToolbarLayout完全折叠后的背景；当滚动位置到达一定的阈值，就会隐藏或者显示Toolbar的背景色；

- 3.Status bar scrim-状态栏背景：当滚动到达一定阈值，状态栏背景会隐藏或显示在状态栏后面；可以通过setStatusBarScrim(Drawable)方法改变这个状态栏背景，这个设置仅仅只能在5.0以上的设备并且设置了fitSystemWindows才能有效果；

- 4.Parallax scrolling children-CollapsingToolbarLayout的子view滚动时的视觉效果：CollapsingToolbarLayout子视图可以选择以视差的方式在布局中滚动；通过在xml中设置CollapsingToolbarLayout子View属性的app:layout_collapseMode = "parallax"和app:layout_collapseParallaxMultiplier = "float"来实现；

- 5.Pinned position children-固定CollapsingToolbarLayout子View的位置（一般固定的是Toolbar的位置）：子view能被固定在任何位置；通过在xml中设置：app:layout_collapseMode="pin"来实现；在实际的测试中发现这个设置无效果，只要在CollapsingToolbarLayout中设置app:layout_scrollFlags="scroll|exitUntilCollapsed”才可以使Toolbar固定在最上面的位置；

> ** 注意：不要在运行时动态人为的给Toolbar添加子View**

#### CollapsingToolbarLayout中常用来设置的属性

| 属性 | 说明 |
|:----- |:---------- |
| app:collapsedTitleGravity="left|center_vertical" | 折叠时Toolbar的标题显示的位置 |
| app:expandedTitleGravity="left|bottom" | 展开时Toolbar的标题显示的位置 |
| app:collapsedTitleTextAppearance=<br>"@style/CollapsingToolbarLayoutTextTheme" | 折叠时Toolbar的字体颜色大小设置；与其对应的还有个app:expandedTitleTextAppearance展开属性；具体的style下面的代码展示；|
| app:contentScrim="@color/colorPrimary" | Toolbar完全折叠时的背景色 |
| app:expandedTitleMarginStart="10dp" | 展开时Toolbar距离左边的间距 |
| app:scrimAnimationDuration="1000" | 设置Toolbar折叠时，颜色变为contentScrim设置的颜色时渐变的时间； |
| app:expandedTitleGravity | 设置toolbar展开时，title所在的位置；相对的还有collpasedTitleGravity等属性； |
| app:titleEnabled="true" | 这个属性默认是true，也就是你不设置就是true；如果设置为false，则在展开和折叠时都只有最上方的toolbar显示toolbar中设置的title，不会显示CollapsingToolbarLayout中设置的title； |

#### 在CollapsingToolbarLayout中子View的ImageView中常设置

app:layout_collapseParallaxMultiplier="0.8"和app:layout_collapseMode="parallax"两个属性，可以在折叠时给用户一个视差效果；

#### layout_collapseMode(折叠模式）说明
| 属性 | 说明 |
|:----- |:---------- |
| pin |    当CollapsingToolbarLayout完全收缩后，Toolbar还可以保留在屏幕上.    |
| parallax |   设置为这个模式时，在内容滚动时，CollapsingToolbarLayout中的View（比如ImageView)也可以同时滚动，实现视差滚动效果，通常和layout_collapseParallaxMultiplier(设置视差因子)搭配使用。视差滚动因子，值为：0~1   |
| none | 不使用任何模式 |
> pin只有在CollapsingToolbarLayout中设置了app:layout_scrollFlags="scroll|exitUntilCollapsed"时Toolbar才会固定到最上面；(不清楚是必须配合使用还是bug，没看源码，有时间在研究)





