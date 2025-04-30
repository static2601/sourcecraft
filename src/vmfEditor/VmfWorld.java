package vmfEditor;

import java.util.ArrayList;

public class VmfWorld {

    public ArrayList<VmfSolid> solids = new ArrayList<>();
    public int id;
    public int mapVersion;
    public String classname;
    public String detailMaterial;
    public String detailVbsp;
    public int maxPropScreenWidth;
    public String skyname;

    public VmfWorld() {
    }
    public VmfSolid getSolids(int index) {
        if(!this.solids.isEmpty()) {
            return this.solids.get(index);
        } else return new VmfSolid();
    }
}
