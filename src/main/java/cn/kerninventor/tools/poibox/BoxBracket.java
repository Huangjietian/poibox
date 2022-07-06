package cn.kerninventor.tools.poibox;


/**
 * <h1>中文注释</h1>
 * <p>
 *     盒子支架，持有一个Poibox对象，以便继承了该对象的子类能够调用poibox的其他模块的方法。
 * </p>
 * @uthor Kern
 * @version 1.0
 */
public class BoxBracket {

    protected Poibox poiBox;

    public BoxBracket(Poibox poiBox) {
        this.poiBox = poiBox;
    }

    public Poibox getParent() {
        return poiBox;
    }

}
