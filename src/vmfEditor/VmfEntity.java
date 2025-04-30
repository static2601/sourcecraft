package vmfEditor;

import java.util.ArrayList;

public class VmfEntity {

    public ArrayList<VmfSolid> solids = new ArrayList<>();
    public ArrayList<VmfEditor> editor = new ArrayList<>();
    public int id;
    public String classname;
    public String disableReceiveShadows;
    public String disableShadows;
    public String origin;
    public String renderAmt;
    public String renderColor;
    public String renderFx;
    public String renderMode;
    public Boolean isRamp = false;

    public VmfEntity() {

    }
    public VmfSolid getSolid() {
        if (!this.solids.isEmpty()) {
            return solids.get(0);
        } else return new VmfSolid();
    }
}
