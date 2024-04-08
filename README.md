# OOAD-UML By 109502552 沈維琳
## Folder: Function Graphic
### Folder: Base Interfaces
裡面包含用來定義共有 function 的 interface
- FunctionGraphic: 可被 draw 的
- Groupable: 可被加入 group
- Selectable: 可被選取 & 移動
### Folder: Base Objects
裡面包含 implement interface 的 abstract base object
- UMLObject: 包含位置、深度、用來顯示的多個 Rectangle (圖形)、用來紀錄連接 UMLLine 的 上下左右的四個 ListArray
- UMLLine: 包含起始與終點的 X, Y 位置、From & To (紀錄連接的 UMLObject)、用來繪圖方便的 shape
- Group: 包含位置、深度、母 & 子 Group、繪製用的 Rectangle (圖形)
### Folder: Enum
用來 switch-case 用的 enums
- Direction: 身兼方向與位置用途的 enum (UP, DOWN, LEFT, RIGHT)
- ToolType: 目前 canvas 需進行的動作 (SELECT, ASSOCIATION, GENERALIZATION, COMPOSITION, CLASS, USECASE)

以下為 UMLObject & UMLLine 的實作:
- Class: A UMLObject，圖形為方形
- UseCase A UMLObject，圖形為橢圓
- Association: A UMLLine，在 to 有未填滿箭頭
- Composition: A UMLLine，在 to 有填滿箭頭
- Generalization: A UMLLine，在 from 有 菱形
皆為Abstract class 的 extends
## Folder: Helper
### HelperMothods
一些簡化 new 流程 (大量相同物件) 或計算的 functions
## Folder: Swing Subclass
### Canvas
有儲存 UMLObject, UMLLine, Group 的三個 ListArray，用來簡化對 object 的操作
methods 包含 select & group 的處理
### CanvasListener
為 MouseListener, MouseMotionListener 的 implementation，用來接收滑鼠位置與呼叫 canvas 上的 method
## App
App 的 main entrance
## Main Frame
負責畫面的 set up
