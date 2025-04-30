package vmfEditor;

import basic.Loggger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import minecraft.Position;

public class OptimizeTest2 {

    private final VmfBsp vmfBsp = new VmfBsp();
    private String outputLog = "\n";
    private int deleteCount = 0;
    private int deleteCountDetail = 0;
    private File file;
    private String writeVMFContent1 = "";
    private String writeVMFContent2 = "";
    private String writeVMFContent3 = "";
    private boolean brushMapping = true;
    private ArrayList<int[]> entityFacesTouching = new ArrayList<>();
    private ArrayList<int[]> solidFacesTouching = new ArrayList<>();

    public static void main(String[] args) {
        new OptimizeTest2();
    }

    public OptimizeTest2() {

        VmfSolid vmfSolid = null;
        VmfSolidSide vmfSolidSide = null;
        VmfEditor vmfEditor = null;
        VmfWorld vmfWorld = null;
        VmfEntity vmfEntity = null;

        //file = new File("C://Users/stati/Desktop/testmergebrushes.vmf");
        file = new File("D://ProjectFiles/test.vmf");
        //file = new File("C://Users/stati/Desktop/testMERGEDBRUSHES01.vmf");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean inEditor = false;
            boolean inEditor2 = false;
            boolean inSide = false;
            boolean inWorld = false;
            boolean inEntity = false;
            boolean writeBefore = true;
            boolean writeAfter = false;
            boolean isFuncDetail = false;
            boolean isFuncIllusionary = false;
            ArrayList<String> lastLines = new ArrayList<>();

            int faceCount = 0;

            String lastLine = "";
            Loggger.log("Reading VMF file...");
            while ((line = br.readLine()) != null) {

                if(writeBefore) vmfBsp.before.add(line);
                if(writeAfter) vmfBsp.after.add(line);

                if(line.equals("world")) {
                    // stop adding line to before array
                    // remove last line
                    writeBefore = false;
                    vmfBsp.before.remove(vmfBsp.before.size()-1);

                    vmfWorld = new VmfWorld();
                    inWorld = true;
                }

                if(line.startsWith("\t\"id\"") && inWorld) {
                    vmfWorld.id = formatLineInt(line, "\t\"id\"");
                }

                if(line.startsWith("\t\"mapversion\"") && inWorld) {
                    vmfWorld.mapVersion = formatLineInt(line, "\t\"mapversion\"");
                }

                if(line.startsWith("\t\"classname\"") && inWorld) {
                    vmfWorld.classname = formatLineStr(line, "\t\"classname\"");
                }

                if(line.startsWith("\t\"detailmaterial\"") && inWorld) {
                    vmfWorld.detailMaterial = formatLineStr(line, "\t\"detailmaterial\"");
                }

                if(line.startsWith("\t\"detailvbsp\"") && inWorld) {
                    vmfWorld.detailVbsp = formatLineStr(line, "\t\"detailvbsp\"");
                }

                if(line.startsWith("\t\"maxpropscreenwidth\"") && inWorld) {
                    vmfWorld.maxPropScreenWidth = formatLineInt(line, "\t\"maxpropscreenwidth\"");
                }

                if(line.startsWith("\t\"skyname\"") && inWorld) {
                    vmfWorld.skyname = formatLineStr(line, "\t\"skyname\"");
                }

                if(line.equals("entity")) {
                    // if no world, turn off here
                    if(writeBefore) {
                        vmfBsp.before.add(line);
                        writeBefore = false;
                        vmfBsp.before.remove(vmfBsp.before.size()-1);
                    }
                    vmfEntity = new VmfEntity();
                    inEntity = true;
                    isFuncDetail = false;
                    isFuncIllusionary = false;
                }

                if(line.startsWith("\t\"id\"") && inEntity) {
                    vmfEntity.id = formatLineInt(line, "\t\"id\"");
                }

                if(line.startsWith("\t\"classname\"") && inEntity) {
                    String classname = formatLineStr(line, "\t\"classname\"");
                    if(classname.equals("func_detail")) {
                        isFuncDetail = true;
                        vmfEntity.classname = classname;
                    }
                    else
                    if(classname.equals("func_illusionary")) {
                        isFuncIllusionary = true;
                        vmfEntity.classname = classname;
                    }
                    // else stop looking through entities
                    else {
                        if(!inWorld && !isFuncDetail && !isFuncIllusionary) {
                            if (!writeBefore && !writeAfter) {
                                writeAfter = true;
                                Loggger.log("Writing After");
                                // add last line since we missed it
                                if(lastLines.size() == 3) {

                                    vmfBsp.after.add(lastLines.get(0));
                                    vmfBsp.after.add(lastLines.get(1));
                                    vmfBsp.after.add(lastLines.get(2));
                                    vmfBsp.after.add(line);
                                    Loggger.log(vmfBsp.after.toString());
                                }
                            }
                        }
                    }
                }
                if(line.startsWith("\t\"ramp\"") && inEntity && isFuncDetail) {
                    vmfEntity.isRamp = true;
                }
                if(line.startsWith("\t\"disablereceiveshadows\"") && inEntity && isFuncIllusionary) {
                    vmfEntity.disableReceiveShadows = formatLineStr(line, "\t\"disablereceiveshadows\"");
                }
                if(line.startsWith("\t\"disableshadows\"") && inEntity && isFuncIllusionary) {
                    vmfEntity.disableShadows = formatLineStr(line, "\t\"disableshadows\"");
                }
                if(line.startsWith("\t\"origin\"") && inEntity && isFuncIllusionary) {
                    vmfEntity.origin = formatLineStr(line, "\t\"origin\"");
                }
                if(line.startsWith("\t\"renderamt\"") && inEntity && isFuncIllusionary) {
                    vmfEntity.renderAmt = formatLineStr(line, "\t\"renderamt\"");
                }
                if(line.startsWith("\t\"rendercolor\"") && inEntity && isFuncIllusionary) {
                    vmfEntity.renderColor = formatLineStr(line, "\t\"rendercolor\"");
                }
                if(line.startsWith("\t\"renderfx\"") && inEntity && isFuncIllusionary) {
                    vmfEntity.renderFx = formatLineStr(line, "\t\"renderfx\"");
                }
                if(line.startsWith("\t\"rendermode\"") && inEntity && isFuncIllusionary) {
                    vmfEntity.renderMode = formatLineStr(line, "\t\"rendermode\"");
                }

                if(lastLine.equals("}") && !inWorld && !inEntity) {
                    if(!writeBefore && !writeAfter) {
                        writeAfter = true;
                        Loggger.log("Writing After");
                        // add last line since we missed it
                        vmfBsp.after.add(line);
                    }
                }

                if(!line.equals("}")) { // end of world/entity array
                    if(line.startsWith("\tsolid")) {
                        vmfSolid = new VmfSolid();
                        faceCount = 0;
                    }

                    if(line.equals("\t}") && !inEditor2) { // end of solid array
                        if(inWorld) {
                            vmfWorld.solids.add(vmfSolid);
                        }
                        else
                        if(inEntity) {
                            if(isFuncDetail || isFuncIllusionary)
                                vmfEntity.solids.add(vmfSolid);
                        }
                    }

                    // start side
                    if(line.equals("\t\tside")) {
                        inSide = true;
                        vmfSolidSide = new VmfSolidSide();
                        if(lastLine.contains("id")) {
                            vmfSolid.id = formatLineInt(lastLine, "\t\t\"id\"");
                        }
                    }

                    if(line.startsWith("\t\t\t\"id\"")) { // id before plane inside side
                        vmfSolidSide.id = formatLineInt(line, "\t\t\t\"id\"");
                    }

                    if(line.startsWith("\t\t\t\"plane\"")) {

                        String coords = formatLineStr(line, "\t\t\t\"plane\"");
                        String[] arrCoords = formatLineStr(coords, "\"").split(" \\(");

                        String[] coordsA = arrCoords[0].replace("(", "")
                            .replace(")", "").split(" ");
                        String[] coordsB = arrCoords[1].replace("(", "")
                            .replace(")", "").split(" ");
                        String[] coordsC = arrCoords[2].replace("(", "")
                            .replace(")", "").split(" ");

                        // brushmapping set to true if converting sourcecraft
                        // false if manually placing in hammer
                        // not automated, default is true
                        if (!brushMapping) {
                            vmfSolidSide.plane1[0] = Double.parseDouble(coordsA[0]);
                            vmfSolidSide.plane1[1] = Double.parseDouble(coordsA[1]);
                            vmfSolidSide.plane1[2] = Double.parseDouble(coordsA[2]);

                            vmfSolidSide.plane2[0] = Double.parseDouble(coordsB[0]);
                            vmfSolidSide.plane2[1] = Double.parseDouble(coordsB[1]);
                            vmfSolidSide.plane2[2] = Double.parseDouble(coordsB[2]);

                            vmfSolidSide.plane3[0] = Double.parseDouble(coordsC[0]);
                            vmfSolidSide.plane3[1] = Double.parseDouble(coordsC[1]);
                            vmfSolidSide.plane3[2] = Double.parseDouble(coordsC[2]);

                            // generate 4th vertice based on face
                            if (faceCount == 0) { // top face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsC[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            } else if (faceCount == 1) { // bottom face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsC[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            } else if (faceCount == 2) { // left(west-facing) face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsA[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            } else if (faceCount == 3) { // right(east-facing) face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsA[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            } else if (faceCount == 4) { // front(north-facing) face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsC[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            } else if (faceCount == 5) { // back(south-facing) face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsC[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            }
                        } else {

                            vmfSolidSide.plane1[0] = Double.parseDouble(coordsA[0]);
                            vmfSolidSide.plane1[1] = Double.parseDouble(coordsA[1]);
                            vmfSolidSide.plane1[2] = Double.parseDouble(coordsA[2]);

                            vmfSolidSide.plane2[0] = Double.parseDouble(coordsB[0]);
                            vmfSolidSide.plane2[1] = Double.parseDouble(coordsB[1]);
                            vmfSolidSide.plane2[2] = Double.parseDouble(coordsB[2]);

                            vmfSolidSide.plane3[0] = Double.parseDouble(coordsC[0]);
                            vmfSolidSide.plane3[1] = Double.parseDouble(coordsC[1]);
                            vmfSolidSide.plane3[2] = Double.parseDouble(coordsC[2]);

                            // generate 4th vertice based on face
                            if(faceCount == 0) { // top face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsC[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsA[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            }
                            else
                            if(faceCount == 1) { // bottom face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsC[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsA[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            }
                            else
                            if(faceCount == 2) { // left(west-facing) face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsA[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            }
                            else
                            if(faceCount == 3) { // right(east-facing) face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsA[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            }
                            else
                            if(faceCount == 4) { // front(north-facing) face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsC[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            }
                            else
                            if(faceCount == 5) { // back(south-facing) face

                                vmfSolidSide.plane4[0] = Double.parseDouble(coordsA[0]);
                                vmfSolidSide.plane4[1] = Double.parseDouble(coordsC[1]);
                                vmfSolidSide.plane4[2] = Double.parseDouble(coordsC[2]);
                                faceCount++;
                            }
                        }
                    }

                    if(line.startsWith("\t\t\t\"material\"")) { // material
                        vmfSolidSide.material = formatLineStr(line, "\t\t\t\"material\"");
                    }

                    if(line.startsWith("\t\t\t\"uaxis\"")) { // uaxis
                        vmfSolidSide.uAxis = formatLineStr(line, "\t\t\t\"uaxis\"");
                    }

                    if(line.startsWith("\t\t\t\"vaxis\"")) { // vaxis
                        vmfSolidSide.vAxis = formatLineStr(line, "\t\t\t\"vaxis\"");
                    }

                    if(line.startsWith("\t\t\t\"rotation\"")) { // rotation
                        vmfSolidSide.rotation = formatLineInt(line, "\t\t\t\"rotation\"");
                    }

                    if(line.startsWith("\t\t\t\"lightmapscale\"")) { // lightmapscale
                        vmfSolidSide.lightMapScale = formatLineInt(line, "\t\t\t\"lightmapscale\"");
                    }

                    if(line.startsWith("\t\t\t\"smoothing_groups\"")) { // smoothing_groups
                        vmfSolidSide.smoothingGroups = formatLineInt(line, "\t\t\t\"smoothing_groups\"");
                    }

                    if(line.equals("\t\t}") && inSide) { // end of side array
                        inSide = false;
                        vmfSolid.sides.add(vmfSolidSide);
                    }
                    // end side

                    // start editor
                    if(line.equals("\t\teditor")) { // beginning of editor
                        inEditor = true;
                        vmfEditor = new VmfEditor();
                    }

                    if(line.startsWith("\t\t\t\"color\"") && inEditor) {
                        String[] astr = formatLineStr(line, "\t\t\t\"color\"").split(" ");
                        vmfEditor.color = new Position(Integer.parseInt(astr[0]),
                                Integer.parseInt(astr[1]), Integer.parseInt(astr[2]));
                    }

                    if(line.startsWith("\t\t\t\"visgroupshown\"") && inEditor) {
                        vmfEditor.visgroupShown = formatLineInt(line, "\t\t\t\"visgroupshown\"");
                    }

                    if(line.startsWith("\t\t\t\"visgroupautoshown\"") && inEditor) {
                        vmfEditor.visgroupAutoShown = formatLineInt(line, "\t\t\t\"visgroupautoshown\"");
                    }

                    if(line.equals("\t\t}") && inEditor) { // end of editor
                        inEditor = false;
                        vmfSolid.editor.add(vmfEditor);
                    }
                    // end editor

                    // editor outside of solid
                    if(line.equals("\teditor")) { // beginning of editor
                        inEditor2 = true;
                        vmfEditor = new VmfEditor();
                    }
                    if(line.startsWith("\t\t\"color\"") && inEditor2) {
                        String[] astr = formatLineStr(line, "\t\t\"color\"").split(" ");
                        vmfEditor.color = new Position(Integer.parseInt(astr[0]),
                                Integer.parseInt(astr[1]), Integer.parseInt(astr[2]));
                    }

                    if(line.startsWith("\t\t\"visgroupshown\"") && inEditor2) {
                        vmfEditor.visgroupShown = formatLineInt(line, "\t\t\"visgroupshown\"");
                    }

                    if(line.startsWith("\t\t\"visgroupautoshown\"") && inEditor2) {
                        vmfEditor.visgroupAutoShown = formatLineInt(line, "\t\t\"visgroupautoshown\"");
                    }

                    if(line.startsWith("\t\t\"logicalpos\"") && inEditor2) {
                        String logpos = formatLineStr(line, "\t\t\"logicalpos\"");
                        String[] strarr = logpos.replace("[", "").replace("]", "").split(" ");
                        vmfEditor.logicalPos = new int[]{Integer.parseInt(strarr[0]), Integer.parseInt(strarr[1])};
                    }

                    if(line.equals("\t}") && inEditor2) { // end of editor
                        inEditor2 = false;
                        vmfEntity.editor.add(vmfEditor);
                    }
                }
                else {
                    if(inWorld) {
                        inWorld = false;
                        vmfBsp.world.add(vmfWorld);
                    }
                    else
                    if(inEntity) {
                        inEntity = false;
                        if(isFuncDetail || isFuncIllusionary) {
                            vmfBsp.entity.add(vmfEntity);
                            isFuncDetail = false;
                            isFuncIllusionary = false;
                        }
                    }
                }
                if(!writeAfter && !writeBefore) {
                    if (lastLines.size() >= 3) {
                        lastLines.remove(0);
                    }
                    lastLines.add(line);
                    lastLine = line;
                }
            }

            // if optimizing Sourcecraft converted maps
            // set to true (default)
            // if optimizing hammer, manually placed brushes,
            // set to false
            // if optimizing brushes from both, then could be problems.
            // would need more code added to handle both
            this.brushMapping = true;
            boolean useMergedData = false;

            if(useMergedData) {
                // remerge previously merged brushes
                // use without mergeSolids and mergeDetail
                mergePreviousBrushes();

            } else {
                mergeStairRamps();
                // merge solids at runtime
                //mergeSolids();
                // merge func_detail/illusionary
                //mergeFuncDetail();

                // save brushes merged to txt file
                // for later recall at runtime
                // use with mergeSolids and mergeDetail
                //facesTouchingToFile("brusheslist");
                //facesTouchingToFile("waterBiome");
                //facesTouchingToFile("woodlandmansion");
            }

            //testBrushSet(16, 20, 0);


            Loggger.log("Building VMF Content...1");
            StringBuilder sbr = new StringBuilder();
            vmfBsp.after.forEach(s -> sbr.append(s).append("\n"));
            writeVMFContent3 += sbr;
            Loggger.log("Building VMF Content...2");
            writeVMFContent2 += vmfBsp.writeVMFMiddle();
            Loggger.log("Building VMF Content...3");
            StringBuilder sb = new StringBuilder();
            vmfBsp.after.forEach(s -> sb.append(s).append("\n"));
            writeVMFContent3 += sb;
            Loggger.log("Building VMF Content...Finished.");
            Loggger.log(outputLog);
            Loggger.log("Solid Brushes Merged: " + deleteCount);
            Loggger.log("Func_Detail Brushes Merged: " + deleteCountDetail);
            Loggger.log("Total Brushed Merged: " + (deleteCountDetail + deleteCount));
            Loggger.log("Writing VMF Content...");
            writeVMF("test3");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void testBrushSet(int id1, int id2, int maxSize) {

        int primaryID = 0;
        int secondaryID = 0;

        for(int z = 0; z < maxSize; z++) {
            int size = vmfBsp.entity.size();
            for (int x = 0; x < size; x++) {

                int ID = vmfBsp.getEntity(x).id;
                if (id1 == ID) {
                    primaryID = vmfBsp.getEntity(x).getSolid().id;
                    Loggger.log("primaryID: "+ primaryID);

                    for (int i = 0; i < size; i++) {
                        int id = vmfBsp.getEntity(i).id;
                        if (id == id2) {
                            secondaryID = vmfBsp.getEntity(i).getSolid().id;
                            //if(secondaryID == primaryID) continue;

                            Loggger.log("primaryID: "+ primaryID +", secondaryID: "+ secondaryID);
                            Loggger.log("Comparing func_detail id1: "+ id1 +", to id2: "+ id2);
                            mergeBrushes(primaryID, secondaryID, 1);
                        }
                    }
                }
            }
        }
    }

    public void mergePreviousBrushes() throws FileNotFoundException {
        getTextFacesTxt();
        int size1 = solidFacesTouching.size();
        for(int i = 0; i < size1; i++) {
            mergeBrushes(i, 0, 2);
        }
        int size2 = entityFacesTouching.size();
        for(int i = 0; i < size2; i++) {
            mergeBrushes(0, i, 3);
        }
    }

    public void getTextFacesTxt() throws FileNotFoundException {
        File filetxt = new File("D://ProjectFiles/brusheslist.txt");
        //file = new File("C://Users/stati/Desktop/testMERGEDBRUSHES01.vmf");
        try (BufferedReader br = new BufferedReader(new FileReader(filetxt))) {
            String line;
            solidFacesTouching.clear();
            entityFacesTouching.clear();
            while ((line = br.readLine()) != null) {
                if(line.startsWith("solids:")) {

                    String[] str = formatLineStr(line, "solids:")
                            .replace("[","").replace("]", "").split(",");

                    solidFacesTouching.add(new int[]{Integer.parseInt(str[0].trim()), Integer.parseInt(str[1].trim()),
                            Integer.parseInt(str[2].trim()), Integer.parseInt(str[3].trim())});
                }
                if(line.startsWith("entities:")) {

                    String[] str = formatLineStr(line, "entities:")
                            .replace("[","").replace("]", "").split(",");

                    entityFacesTouching.add(new int[]{Integer.parseInt(str[0].trim()), Integer.parseInt(str[1].trim()),
                            Integer.parseInt(str[2].trim()), Integer.parseInt(str[3].trim())});
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        /** remove keyword and quotes from a string and trim */
    public String formatLineStr(String line, String keyword) {
        return line.replace(keyword, "").replace("\"", "").trim();
    }

    /** remove keyword and quotes from a string, trim and parse to int */
    public int formatLineInt(String line, String keyword) {
        return Integer.parseInt(line.replace(keyword, "")
                .replace("\"", "").trim());
    }
    public void mergeStairRamps() {

        // get all solids in map, run loop through
        // get solids, then their ids
        // number of cycles to do, at least needed
        // to properly merge all brushes
        for(int z = 0; z < 10; z++) {
            int size_x = vmfBsp.entity.size();
            for (int x = 0; x < size_x; x++) {
                if(vmfBsp.getEntity(x).isRamp) {
                    VmfSolid ent_solid = vmfBsp.getEntity(x).getSolid();
                    int primaryID = ent_solid.id;

                    int size_i = vmfBsp.entity.size();
                    for (int i = 0; i < size_i; i++) {
                        if(vmfBsp.getEntity(i).isRamp) {
                            ent_solid = vmfBsp.getEntity(i).getSolid();
                            int id = ent_solid.id;
                            if (id == primaryID) continue;
                            else {
                                mergeBrushes(primaryID, id, 1);
                            }
                        }
                    }
                }
            }
        }
    }
    public void mergeSolids() {

        // get all solids in map, run loop through
        // get solids, then their ids
        // number of cycles to do, at least needed
        // to properly merge all brushes
        for(int z = 0; z < 2; z++) {

            ArrayList<VmfSolid> solids = vmfBsp.world.get(0).solids;
            int size_x = solids.size();
            for (int x = 0; x < size_x; x++) {
                int primaryID = solids.get(x).id;

                int size_i = solids.size();
                for (int i = 0; i < size_i; i++) {
                    int id = solids.get(i).id;
                    if (id == primaryID) continue;
                    else {
                        mergeBrushes(primaryID, id, 0);
                    }
                }
            }
        }
    }

    public void mergeFuncDetail() {

        // get all solids in map, run loop through
        // get solids, then their ids
        // number of cycles to do, at least needed
        // to properly merge all brushes
        for(int z = 0; z < 2; z++) {
            int size_x = vmfBsp.entity.size();
            for (int x = 0; x < size_x; x++) {
                VmfSolid ent_solid = vmfBsp.getEntity(x).getSolid();
                int primaryID = ent_solid.id;

                int size_i = vmfBsp.entity.size();
                for (int i = 0; i < size_i; i++) {
                    ent_solid = vmfBsp.getEntity(i).getSolid();
                    int id = ent_solid.id;
                    if (id == primaryID) continue;
                    else {
                        mergeBrushes(primaryID, id, 1);
                    }
                }
            }
        }
    }

    public void mergeBrushes(int ID1, int ID2, int test) {

        int[] testFaces = new int[0];
        if(test <= 1)
            testFaces = testFindMatchingFaces(ID1, ID2, test);
        if (test > 1 || testFaces.length > 0) {
            // save brushes that touch to list so we can merge more quickly
            // without having to rescan for faces touching
            if(test == 0) solidFacesTouching.add(testFaces);
            if(test == 1) entityFacesTouching.add(testFaces);
            if(test > 1) {

                if (test == 2) {
                    // do solidFacesTouching
                    testFaces = solidFacesTouching.get(ID1);
                    test = 0;
                }
                if (test == 3) {
                    // do entityFacesTouching
                    testFaces = entityFacesTouching.get(ID2);
                    test = 1;
                }
            }

            if(test == 0) Loggger.log("Testing non-entity solids...");
            if(test == 1) Loggger.log("Testing func_detail/func_illusionary...");

            Loggger.log("testFaces: " +"[index of solidA: "+ testFaces[0]+", Side: "+ testFaces[1]
                    +", index of solidB: "+ testFaces[2]+", side: "+ testFaces[3]+"]");

            // get top face (side 0) of first brush
            ArrayList<VmfSolidSide> sidesA = new ArrayList<>();
            ArrayList<VmfSolidSide> sidesB = new ArrayList<>();
            if(test == 0) {
                sidesA = vmfBsp.getWorld().getSolids(testFaces[0]).sides;
                sidesB = vmfBsp.getWorld().getSolids(testFaces[2]).sides;
            }
            if(test == 1) {
                sidesA = vmfBsp.getEntity(testFaces[0]).getSolid().sides;
                sidesB = vmfBsp.getEntity(testFaces[2]).getSolid().sides;
            }

            VmfSolidSide firstTop = sidesA.get(0);
            VmfSolidSide firstBottom = sidesA.get(1);

            VmfSolidSide secondTop = sidesB.get(0);
            VmfSolidSide secondBottom = sidesB.get(1);

            double height = Math.abs(firstTop.plane1[2] - firstBottom.plane1[2]);
            Loggger.log("height: " + height);
            Loggger.log("firstTop.planeA.z: " + firstTop.plane1[2] + ", firstBottom.planeA.z: " + firstBottom.plane1[2]);

            //extending top face only
            if (testFaces[1] == 0) { // top face

                firstTop.plane1 = secondTop.plane1;
                firstTop.plane2 = secondTop.plane2;
                firstTop.plane3 = secondTop.plane3;
                firstTop.plane4 = secondTop.plane4;

            } else if (testFaces[1] == 1) { // bottom face

                firstBottom.plane1 = secondBottom.plane1;
                firstBottom.plane2 = secondBottom.plane2;
                firstBottom.plane3 = secondBottom.plane3;
                firstBottom.plane4 = secondBottom.plane4;

            } else if (testFaces[1] == 2) { // west face

                if(!brushMapping) {

                    firstTop.plane1 = secondTop.plane1;
                    firstTop.plane4 = secondTop.plane4;
                    firstBottom.plane1 = secondBottom.plane1;
                    firstBottom.plane4 = secondBottom.plane4;

                } else {

                    firstTop.plane1 = secondTop.plane1;
                    firstTop.plane2 = secondTop.plane2;
                    firstBottom.plane1 = secondBottom.plane1;
                    firstBottom.plane2 = secondBottom.plane2;

                }
            } else if (testFaces[1] == 3) { // east face

                if(!brushMapping) {

                    firstTop.plane2 = secondTop.plane2;
                    firstTop.plane3 = secondTop.plane3;
                    firstBottom.plane2 = secondBottom.plane2;
                    firstBottom.plane3 = secondBottom.plane3;

                } else {

                    firstTop.plane3 = secondTop.plane3;
                    firstTop.plane4 = secondTop.plane4;
                    firstBottom.plane3 = secondBottom.plane3;
                    firstBottom.plane4 = secondBottom.plane4;
                }
            } else if (testFaces[1] == 4) { // north face

                if(!brushMapping) {

                    firstTop.plane1 = secondTop.plane1;
                    firstTop.plane2 = secondTop.plane2;
                    firstBottom.plane3 = secondBottom.plane3;
                    firstBottom.plane4 = secondBottom.plane4;

                } else {

                    firstTop.plane2 = secondTop.plane2;
                    firstTop.plane3 = secondTop.plane3;
                    firstBottom.plane1 = secondBottom.plane1;
                    firstBottom.plane4 = secondBottom.plane4;
                }
            } else if (testFaces[1] == 5) { // south face

                if(!brushMapping) {

                    firstTop.plane3 = secondTop.plane3;
                    firstTop.plane4 = secondTop.plane4;
                    firstBottom.plane1 = secondBottom.plane1;
                    firstBottom.plane2 = secondBottom.plane2;

                } else {

                    firstTop.plane1 = secondTop.plane1;
                    firstTop.plane4 = secondTop.plane4;
                    firstBottom.plane2 = secondBottom.plane2;
                    firstBottom.plane3 = secondBottom.plane3;
                }
            }

            // construct full brush
            // side 0 (top)
            // side 1 (bottom)
            // already done from top work

            if(!brushMapping) {

                // side 2 (west facing)
                sidesA.get(2).plane1 = sidesA.get(0).plane1;
                sidesA.get(2).plane2 = sidesA.get(0).plane4;
                sidesA.get(2).plane3 = sidesA.get(1).plane1;
                sidesA.get(2).plane4 = sidesA.get(1).plane4;

                // side 3 (east facing)
                sidesA.get(3).plane1 = sidesA.get(0).plane3;
                sidesA.get(3).plane2 = sidesA.get(0).plane2;
                sidesA.get(3).plane3 = sidesA.get(1).plane3;
                sidesA.get(3).plane4 = sidesA.get(1).plane2;

                // side 4 (north facing)
                sidesA.get(4).plane1 = sidesA.get(0).plane2;
                sidesA.get(4).plane2 = sidesA.get(0).plane1;
                sidesA.get(4).plane3 = sidesA.get(1).plane4;
                sidesA.get(4).plane4 = sidesA.get(1).plane3;

                // side 5 (south facing)
                sidesA.get(5).plane1 = sidesA.get(0).plane4;
                sidesA.get(5).plane2 = sidesA.get(0).plane3;
                sidesA.get(5).plane3 = sidesA.get(1).plane2;
                sidesA.get(5).plane4 = sidesA.get(1).plane1;

            } else {

                // side 2 (west facing)
                sidesA.get(2).plane1 = sidesA.get(1).plane2;
                sidesA.get(2).plane2 = sidesA.get(1).plane1;
                sidesA.get(2).plane3 = sidesA.get(0).plane2;
                sidesA.get(2).plane4 = sidesA.get(0).plane1;

                // side 3 (east facing)
                sidesA.get(3).plane1 = sidesA.get(1).plane4;
                sidesA.get(3).plane2 = sidesA.get(1).plane3;
                sidesA.get(3).plane3 = sidesA.get(0).plane4;
                sidesA.get(3).plane4 = sidesA.get(0).plane3;

                // side 4 (north facing)
                sidesA.get(4).plane1 = sidesA.get(1).plane1;
                sidesA.get(4).plane2 = sidesA.get(1).plane4;
                sidesA.get(4).plane3 = sidesA.get(0).plane3;
                sidesA.get(4).plane4 = sidesA.get(0).plane2;

                // side 5 (south facing)
                sidesA.get(5).plane1 = sidesA.get(1).plane3;
                sidesA.get(5).plane2 = sidesA.get(1).plane2;
                sidesA.get(5).plane3 = sidesA.get(0).plane1;
                sidesA.get(5).plane4 = sidesA.get(0).plane4;

            }

            // now, delete second brush
            int sid = -1;
            if(test == 0) sid = vmfBsp.getWorld().getSolids(testFaces[2]).id;
            if(test == 1) sid = vmfBsp.getEntity(testFaces[2]).getSolid().id;
            outputLog += "deleting func_detail brush id, entity index: " + sid + ", " + (testFaces[2]) + "\n";

            Loggger.log("primary func_detail brush id, entity index: " + ID1 + ", " + (testFaces[0]));
            Loggger.log("deleting func_detail brush sid, entity index: " + sid + ", " + (testFaces[2]));
            Loggger.log("deleting func_detail brush just_id, entity index: " + ID2 + ", " + (testFaces[2]));
            if(test == 0) {

                vmfBsp.getWorld().solids.remove(testFaces[2]);
                vmfBsp.getWorld().solids.add(testFaces[2], new VmfSolid());
                //vmfBsp.getWorld().solidsToRemove.add(testFaces[2]);

                deleteCount++;
                // start over loop
                // mergeSolids();
                // break;
            }
            else {
                vmfBsp.entity.remove(testFaces[2]);
                vmfBsp.entity.add(testFaces[2], new VmfEntity());
                //vmfBsp.entitiesToRemove.add(testFaces[2]);

                deleteCountDetail++;
                // start over loop
                // mergeFuncDetail();
                // break;
            }
        }
    }

    /** returns index of solid(sA)_0, index of side(sA)_1,
     * index of solid(sB)_2, index of side(sB)_3 */
    public int[] testFindMatchingFaces(int sA, int sB, int test) {

        // returns 6 faces/sides, points of 6 sets of 4
        // plane a, b, c, d
        int[] facesTouching = {};
        int solidID1 = 0;
        int idxOfSolid1 = 0;
        int solidID2 = 0;
        int idxOfSolid2 = 0;

        ArrayList<VmfEntity> entityArr = vmfBsp.entity;
        ArrayList<VmfSolid> solidArr = vmfBsp.getWorld().solids;

        if(test == 0) {
            // solids
            int size = solidArr.size();
            for (int i = 0; i < size; i++) {
                if (solidArr.get(i).id == sA) {
                    solidID1 = solidArr.get(i).id;
                    idxOfSolid1 = i;
                }
                if (solidArr.get(i).id == sB) {
                    solidID2 = solidArr.get(i).id;
                    idxOfSolid2 = i;
                }
            }
        }
        else
        if(test == 1) {
            // func_detail and func_illusionary
            int size = entityArr.size();
            for (int i = 0; i <size; i++) {
                if (vmfBsp.getEntity(i).getSolid().id == sA) {
                    solidID1 = vmfBsp.getEntity(i).getSolid().id;
                    idxOfSolid1 = i;
                }
                if (vmfBsp.getEntity(i).getSolid().id == sB) {
                    solidID2 = vmfBsp.getEntity(i).getSolid().id;
                    idxOfSolid2 = i;
                }
            }
        }

        // get vertices on all sides of both brushes we are comparing
        VmfSolid solids = new VmfSolid();
        if(test == 0) solids = solidArr.get(idxOfSolid1);
        if(test == 1) solids = entityArr.get(idxOfSolid1).getSolid();
        ArrayList<ArrayList<double[]>> points = solids.getBrushPoints(solidID1);

        //returns 6 faces/sides points of
        if(test == 0) solids = solidArr.get(idxOfSolid2);
        if(test == 1) solids = entityArr.get(idxOfSolid2).getSolid();
        ArrayList<ArrayList<double[]>> cPoints = solids.getBrushPoints(solidID2);

        // c max 6 (sides) 0 - 5
        int cSize = cPoints.size();
        for(int c = 0; c < cSize; c++) {

            //String material2 = solidArr.get(idxOfSolid2).sides.get(c).getBrushMaterial();
            // get material of face(c) in check and compare to other (i)
            ArrayList<String> mat2 = getBrushMaterial(idxOfSolid2, test);
            //String matt2 = solids.sides.get(c).material;
            String material2 = mat2.get(c);

            // loops through all 24 points of 6 sides of 1st solid id
            // i = plane( A(xyz), B(xyz), C(xyz), D(xyz) ) of 1/6 sides
            int size = points.size();
            iLoop:
            for (int i = 0; i < size; i++) {
                int trueCount = 0;
                // get material of face(i) in check and compare to other (c)
                ArrayList<String> mat1 = getBrushMaterial(idxOfSolid1, test);
                String material1 = mat1.get(i);
                //String material1 = entityArr.get(idxOfSolid1).getSolid().sides.get(i).getBrushMaterial();

                if(material1.contains("glazed_terracotta")
                || material2.contains("glazed_terracotta")
                //|| material1.contains("tools/toolsplayerclip")
                //|| material2.contains("tools/toolsplayerclip")
                )
                    break;

                if(material1.equals("TOOLS/TOOLSNODRAW")
                || material1.contains("WATER"))
                    trueCount++;
                else
                if(material1.equals(material2)) {
                    trueCount++;
                }

                // so 2 brushes come in looking to be compared against eachother to see if they touch
                // if they dont, needs to return asap
                //only compare each side of brush one against the opposite brush of the same but opposite face?
                // need to reduce operations as much as possible, too slow processing!!


                // if faces in-check are of 2 different materials, should we
                // stop comparing those to brushes entirely or continue checking?
                // could be a block anywhere in the map or right next to it,
                // some no?
                // had less brushes merged after adding else break -924 brushes removed
                //else break;
                else continue;

                // testing against this solid of 2nd id, 24 points, 6 sides
                // need to find which points are the same, meaning faces are shared
                // x = vertices in face

                // add vertices to test to list so we can remove them
                // when a match is found so they are not tested against twice
                ArrayList<double[]> cPointsList = new ArrayList<>();
                cPointsList.add(cPoints.get(c).get(0));
                cPointsList.add(cPoints.get(c).get(1));
                cPointsList.add(cPoints.get(c).get(2));
                cPointsList.add(cPoints.get(c).get(3));

                xLoop:
                for (int x = 0; x < 4; x++) {

                    // compare all  6 sides, first compare: top(i).plane1(x).x to cPoints.get(sides).get(planeA-D(xyz))
                    // 6 sides, 4 planes A,B,C,D, 3 points x,y,z
                    // if top(i).planeA(x) is equal to 1 of the planes, add trueCount, test 3 more times

                    // if vertice is equal to a point already compared against, then skip
                    // if points does not equal function scan through arraylist for an equal
                    // if found return true/false

                    // for each plane in array of 4 planes of brush2
                    for(double[] plane : cPointsList) {
                        // comparing one plane of brush1 to one on brush2
                        if(solids.comparePoints(points.get(i).get(x), plane)) {
                            trueCount++;
                            // break from this for each loop, compare the other planes
                            break;
                        }
                        if(trueCount > 3) {
                            Loggger.log("\n");
                            Loggger.log("i: " + i + ", c: " + c);
                            Loggger.log("trueCount: " + trueCount);
                            Loggger.log("points.get(i).get(x): " + Arrays.toString(points.get(i).get(0)) + " | " + Arrays.toString(cPoints.get(c).get(0)));
                            Loggger.log("points.get(i).get(x): " + Arrays.toString(points.get(i).get(1)) + " | " + Arrays.toString(cPoints.get(c).get(1)));
                            Loggger.log("points.get(i).get(x): " + Arrays.toString(points.get(i).get(2)) + " | " + Arrays.toString(cPoints.get(c).get(2)));
                            Loggger.log("points.get(i).get(x): " + Arrays.toString(points.get(i).get(3)) + " | " + Arrays.toString(cPoints.get(c).get(3)));
                            //Loggger.log("cPoints Array:        " + Arrays.toString(plane));
                        }
                    }
                    // if one plane of 4 is not found at all stop searching?
                    // one vertice is not found in the side, non-other would be either?
                    // stop unnecessary checking, continue to next side
                    // could we even just check the side of the oppoiste side?
                    // could a block be rotated?
                    // could just check the opposite side of side we are testing,
                    // but must check every side of brush 1

                    // could we also, on the second loop, exclude brushes that found no sides touching?

                    // if on planes found sharing same plane of 4, check another side
                    if(trueCount <= 1) continue iLoop;

                }

                if(trueCount == 5) {
                    StringBuilder sbr = new StringBuilder();
                    /** { idxOfSolid1, side 0-5(i), idxOfSolid2, side 0-5(c) }
                     * side(i) and side(c) should only be 1 off, eg 0 and 1, 1 and 2, 2 and 1, never 0 and 2?
                     * side 0 = top, 1 = bottom, 2 = west, 3 = east, 4 = north, 5 = south */
                    facesTouching = new int[]{idxOfSolid1, i, idxOfSolid2, c};
                    sbr.append("\n");
                    if(test == 0) sbr.append("TRUECOUNT == 5 <-----------------------------------------").append("\n");
                    if(test == 1) sbr.append("TRUECOUNT == 5 <+++++++++++++++++++++++++++++++++++++++++").append("\n");
                    sbr.append("trueCount: ").append(trueCount).append("\n");
                    sbr.append("solidID1: ").append(solidID1).append(", solidID2: ").append(solidID2).append("\n");
                    sbr.append("points.get(i): ").append(i).append(", cPoints.get(c): ").append(c).append("\n");
                    sbr.append("material1: ").append(material1).append(", material2: ").append(material2).append("\n");

                    sbr.append("points/plane1: ").append(Arrays.toString(points.get(i).get(0)))
                    .append(", cPoints/plane1: ").append(Arrays.toString(cPoints.get(c).get(0))).append("\n");
                    sbr.append("points/plane2: ").append(Arrays.toString(points.get(i).get(1)))
                    .append(", cPoints/plane2: ").append(Arrays.toString(cPoints.get(c).get(1))).append("\n");
                    sbr.append("points/plane3: ").append(Arrays.toString(points.get(i).get(2)))
                    .append(", cPoints/plane3: ").append(Arrays.toString(cPoints.get(c).get(2))).append("\n");
                    sbr.append("points/plane4: ").append(Arrays.toString(points.get(i).get(3)))
                    .append(", cPoints/plane4: ").append(Arrays.toString(cPoints.get(c).get(3))).append("\n");

                    Loggger.log(sbr.toString());

                    return facesTouching;
                }
            }
        }
        return facesTouching;
    }

    private boolean testComparedFaces(int i, int c) {
        return (i - c) == 1 || (i - c) == -1;
    }

    public ArrayList<String> getBrushMaterial(int index, int test) {
        ArrayList<String> material = new ArrayList<>();
        if(test == 0) {
            ArrayList<VmfSolidSide> sides = vmfBsp.world.get(0).solids.get(index).sides;
            for (VmfSolidSide side : sides) {
                material.add(side.getBrushMaterial());
            }
            return material;
        }
        else
        if(test == 1) {
            ArrayList<VmfSolidSide> sides = vmfBsp.entity.get(index).solids.get(0).sides;
            for (VmfSolidSide side : sides) {
                material.add(side.getBrushMaterial());
            }
            return material;
        }
        return material;
    }
    public void writeVMF(String filename) {
        try {
            String fileDir = file.getParent();
            FileWriter myWriter = new FileWriter(fileDir +"/"+ filename+".vmf");
            myWriter.write(writeVMFContent1 + "\n"+ writeVMFContent2 +"\n"+ writeVMFContent3);
            myWriter.close();
            Loggger.log("Successfully wrote file to " + fileDir +"/"+ filename +".vmf");
        } catch (IOException e) {
            Loggger.log("An error occurred.");
            e.printStackTrace();
        }
    }
    public void facesTouchingToFile(String filename) throws IOException {
        try {
            String fileDir = "D://ProjectFiles";
            //String filename = "brusheslist";
            FileWriter myWriter = new FileWriter(fileDir +"/"+ filename+".txt");

            int size = solidFacesTouching.size();
            for (int i = 0; i < size; i++) {
                myWriter.write("solids:"+Arrays.toString(solidFacesTouching.get(i)) + "\n");
            }
            size = entityFacesTouching.size();
            for (int i = 0; i < size; i++) {
                myWriter.write("entities:"+Arrays.toString(entityFacesTouching.get(i)) + "\n");
            }
            myWriter.close();
            Loggger.log("Successfully wrote file to " + fileDir +"/"+ filename +".vmf");
        } catch (IOException e) {
            Loggger.log("An error occurred.");
            e.printStackTrace();
        }
    }
    public enum eSides {
        TOP,
        BOTTOM,
        WEST,
        EAST,
        NORTH,
        SOUTH
    }
    public void printVmfToConsole() {

        vmfBsp.world.forEach(world -> {
            Loggger.log("world");
            Loggger.log("\tid: " + world.id);
            Loggger.log("\tmapversion: " + world.mapVersion);
            Loggger.log("\tclassname: " + world.classname);
            Loggger.log("\tdetailmaterial: " + world.detailMaterial);
            Loggger.log("\tdetailvbsp: " + world.detailVbsp);
            Loggger.log("\tmaxpropscreenwidth: " + world.maxPropScreenWidth);
            Loggger.log("\tskyname: " + world.skyname);

            world.solids.forEach(solids -> {
                Loggger.log("\tSolid");
                Loggger.log("\tid: " + solids.id);
                solids.sides.forEach(sides -> {
                    Loggger.log("\t\tside");
                    Loggger.log("\t\t\tid: " + sides.id);
                    Loggger.log("\t\t\tplane: (" + Arrays.toString(sides.plane1) + ") (" + Arrays.toString(sides.plane2)
                            + ") (" + Arrays.toString(sides.plane3) + ") (" + Arrays.toString(sides.plane4) + ")");
                    Loggger.log("\t\t\tmaterial: " + sides.material);
                    Loggger.log("\t\t\tuaxis: " + sides.uAxis);
                    Loggger.log("\t\t\tvaxis: " + sides.vAxis);
                    Loggger.log("\t\t\trotation: " + sides.rotation);
                    Loggger.log("\t\t\tlightmapscale: " + sides.lightMapScale);
                    Loggger.log("\t\t\tsmoothing_groups: " + sides.smoothingGroups);
                });
                solids.editor.forEach(e -> {
                    Loggger.log("\t\teditor");
                    Loggger.log("\t\t\tcolor: " + e.color);
                    Loggger.log("\t\t\tvisgroupshown: " + e.visgroupShown);
                    Loggger.log("\t\t\tvisgroupautoshown: " + e.visgroupAutoShown);
                });
            });
        });
        vmfBsp.entity.forEach(entity -> {
            Loggger.log("entity");
            Loggger.log("\tid: " + entity.id);
            Loggger.log("\tclassname: " + entity.classname);
            entity.solids.forEach(solids -> {
                Loggger.log("\tSolid");
                Loggger.log("\tid: " + solids.id);
                solids.sides.forEach(sides -> {
                    Loggger.log("\t\tside");
                    Loggger.log("\t\t\tid: " + sides.id);
                    Loggger.log("\t\t\tplane: (" + Arrays.toString(sides.plane1) + ") (" + Arrays.toString(sides.plane2)
                            + ") (" + Arrays.toString(sides.plane3) + ") (" + Arrays.toString(sides.plane4) + ")");
                    Loggger.log("\t\t\tmaterial: " + sides.material);
                    Loggger.log("\t\t\tuaxis: " + sides.uAxis);
                    Loggger.log("\t\t\tvaxis: " + sides.vAxis);
                    Loggger.log("\t\t\trotation: " + sides.rotation);
                    Loggger.log("\t\t\tlightmapscale: " + sides.lightMapScale);
                    Loggger.log("\t\t\tsmoothing_groups: " + sides.smoothingGroups);
                });
                solids.editor.forEach(e -> {
                    Loggger.log("\t\teditor");
                    Loggger.log("\t\t\tcolor: " + e.color);
                    Loggger.log("\t\t\tvisgroupshown: " + e.visgroupShown);
                    Loggger.log("\t\t\tvisgroupautoshown: " + e.visgroupAutoShown);
                });
            });
            entity.editor.forEach(e -> {
                Loggger.log("\teditor");
                Loggger.log("\t\tcolor: " + e.color);
                Loggger.log("\t\tvisgroupshown: " + e.visgroupShown);
                Loggger.log("\t\tvisgroupautoshown: " + e.visgroupAutoShown);
                Loggger.log("\t\tlogicalpos: [" + e.logicalPos[0]+ " " + e.logicalPos[1] + "]");
            });
        });
    }
}