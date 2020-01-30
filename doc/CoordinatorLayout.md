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





