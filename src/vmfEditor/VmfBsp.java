package vmfEditor;

import java.util.ArrayList;

public class VmfBsp {

    public ArrayList<VmfWorld> world = new ArrayList<>();
    public ArrayList<VmfEntity> entity = new ArrayList<>();
    //public ArrayList<Integer> entitiesToRemove = new ArrayList<>();
    public ArrayList<String> before = new ArrayList<>();
    public ArrayList<String> after = new ArrayList<>();

    public VmfBsp() {}

    public StringBuilder writeVMFMiddle() {
        StringBuilder sb = new StringBuilder();
        this.world.forEach(w ->{

            sb.append("world").append("\n");
            sb.append("{").append("\n");
            sb.append("\t\"id\" \"").append(w.id).append("\"").append("\n");
            sb.append("\t\"mapversion\" \"").append(w.mapVersion).append("\"").append("\n");
            sb.append("\t\"classname\" \"").append(w.classname).append("\"").append("\n");
            sb.append("\t\"detailmaterial\" \"").append(w.detailMaterial).append("\"").append("\n");
            sb.append("\t\"detailvbsp\" \"").append(w.detailVbsp).append("\"").append("\n");
            sb.append("\t\"maxpropscreenwidth\" \"").append(w.maxPropScreenWidth).append("\"").append("\n");
            sb.append("\t\"skyname\" \"").append(w.skyname).append("\"").append("\n");

            w.solids.forEach(s ->{
                if(s.id != 0) {
                    sb.append("\tsolid").append("\n");
                    sb.append("\t{").append("\n");
                    sb.append("\t\t\"id\" \"").append(s.id).append("\"").append("\n");

                    s.sides.forEach(ss -> {
                        sb.append("\t\tside").append("\n");
                        sb.append("\t\t{").append("\n");
                        sb.append("\t\t\t\"id\" \"").append(ss.id).append("\"").append("\n");
                        sb.append("\t\t\t\"plane\" \"")
                                .append("(").append(ss.plane1[0]).append(" ").append(ss.plane1[1]).append(" ").append(ss.plane1[2]).append(") ")
                                .append("(").append(ss.plane2[0]).append(" ").append(ss.plane2[1]).append(" ").append(ss.plane2[2]).append(") ")
                                .append("(").append(ss.plane3[0]).append(" ").append(ss.plane3[1]).append(" ").append(ss.plane3[2]).append(")\"")
                                .append("\n");
                        sb.append("\t\t\t\"material\" \"").append(ss.material).append("\"").append("\n");
                        sb.append("\t\t\t\"uaxis\" \"").append(ss.uAxis).append("\"").append("\n");
                        sb.append("\t\t\t\"vaxis\" \"").append(ss.vAxis).append("\"").append("\n");
                        sb.append("\t\t\t\"rotation\" \"").append(ss.rotation).append("\"").append("\n");
                        sb.append("\t\t\t\"lightmapscale\" \"").append(ss.lightMapScale).append("\"").append("\n");
                        sb.append("\t\t\t\"smoothing_groups\" \"").append(ss.smoothingGroups).append("\"").append("\n");
                        sb.append("\t\t}").append("\n");
                    });

                    s.editor.forEach(e -> {
                        sb.append("\t\teditor").append("\n");
                        sb.append("\t\t{").append("\n");
                        sb.append("\t\t\t\"color\" \"").append(e.color.x).append(" ").append(e.color.y).append(" ").append(e.color.z).append("\"").append("\n");
                        sb.append("\t\t\t\"visgroupshown\" \"").append(e.visgroupShown).append("\"").append("\n");
                        sb.append("\t\t\t\"visgroupautoshown\" \"").append(e.visgroupAutoShown).append("\"").append("\n");
                        sb.append("\t\t}").append("\n");
                    });

                    sb.append("\t}").append("\n");
                }
            });

            sb.append("}").append("\n");
        });

        entity.forEach(ent -> {
            if(ent.id != 0) {
                sb.append("entity").append("\n");
                sb.append("{").append("\n");
                sb.append("\t\"id\" \"").append(ent.id).append("\"").append("\n");
                sb.append("\t\"classname\" \"").append(ent.classname).append("\"").append("\n");
                if (ent.isRamp)
                    sb.append("\t\"ramp\" \"").append("1").append("\"").append("\n");

                if (ent.classname != null) {
                    if (ent.classname.equals("func_illusionary")) {
                        sb.append("\t\"disablereceiveshadows\" \"").append(ent.disableReceiveShadows).append("\"").append("\n");
                        sb.append("\t\"disableshadows\" \"").append(ent.disableShadows).append("\"").append("\n");
                        sb.append("\t\"origin\" \"").append(ent.origin).append("\"").append("\n");
                        sb.append("\t\"renderamt\" \"").append(ent.renderAmt).append("\"").append("\n");
                        sb.append("\t\"rendercolor\" \"").append(ent.renderColor).append("\"").append("\n");
                        sb.append("\t\"renderfx\" \"").append(ent.renderFx).append("\"").append("\n");
                        sb.append("\t\"rendermode\" \"").append(ent.renderMode).append("\"").append("\n");
                    }
                }

                ent.solids.forEach(s -> {
                    sb.append("\tsolid").append("\n");
                    sb.append("\t{").append("\n");
                    sb.append("\t\t\"id\" \"").append(s.id).append("\"").append("\n");

                    s.sides.forEach(ss -> {
                        sb.append("\t\tside").append("\n");
                        sb.append("\t\t{").append("\n");
                        sb.append("\t\t\t\"id\" \"").append(ss.id).append("\"").append("\n");
                        sb.append("\t\t\t\"plane\" \"")
                                .append("(").append(ss.plane1[0]).append(" ").append(ss.plane1[1]).append(" ").append(ss.plane1[2]).append(") ")
                                .append("(").append(ss.plane2[0]).append(" ").append(ss.plane2[1]).append(" ").append(ss.plane2[2]).append(") ")
                                .append("(").append(ss.plane3[0]).append(" ").append(ss.plane3[1]).append(" ").append(ss.plane3[2]).append(")\"")
                                .append("\n");
                        sb.append("\t\t\t\"material\" \"").append(ss.material).append("\"").append("\n");
                        sb.append("\t\t\t\"uaxis\" \"").append(ss.uAxis).append("\"").append("\n");
                        sb.append("\t\t\t\"vaxis\" \"").append(ss.vAxis).append("\"").append("\n");
                        sb.append("\t\t\t\"rotation\" \"").append(ss.rotation).append("\"").append("\n");
                        sb.append("\t\t\t\"lightmapscale\" \"").append(ss.lightMapScale).append("\"").append("\n");
                        sb.append("\t\t\t\"smoothing_groups\" \"").append(ss.smoothingGroups).append("\"").append("\n");
                        sb.append("\t\t}").append("\n");
                    });

                    s.editor.forEach(e -> {
                        sb.append("\t\teditor").append("\n");
                        sb.append("\t\t{").append("\n");
                        sb.append("\t\t\t\"color\" \"").append(e.color.x).append(" ").append(e.color.y).append(" ").append(e.color.z).append("\"").append("\n");
                        sb.append("\t\t\t\"visgroupshown\" \"").append(e.visgroupShown).append("\"").append("\n");
                        sb.append("\t\t\t\"visgroupautoshown\" \"").append(e.visgroupAutoShown).append("\"").append("\n");
                        sb.append("\t\t}").append("\n");
                    });

                    sb.append("\t}").append("\n");
                    sb.append("\teditor").append("\n");
                    sb.append("\t{").append("\n");
                    ent.editor.forEach(ed -> {
                        sb.append("\t\t\"color\" \"").append(ed.color.x).append(" ").append(ed.color.y).append(" ").append(ed.color.z).append("\"").append("\n");
                        sb.append("\t\t\"visgroupshown\" \"").append(ed.visgroupShown).append("\"").append("\n");
                        sb.append("\t\t\"visgroupautoshown\" \"").append(ed.visgroupAutoShown).append("\"").append("\n");
                        sb.append("\t\t\"logicalpos\" \"[").append(ed.logicalPos[0]).append(" ").append(ed.logicalPos[1]).append("]\"").append("\n");
                        sb.append("\t}").append("\n");

                    });
                });
                sb.append("}").append("\n");
            }
        });
        return sb;
    }
    public VmfEntity getEntity(int index) {
        if(!this.entity.isEmpty()) {
            return this.entity.get(index);
        } else return new VmfEntity();

    }
    public VmfWorld getWorld() {
        if(!this.world.isEmpty()) {
            return this.world.get(0);
        } else return new VmfWorld();

    }
}
