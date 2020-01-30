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


