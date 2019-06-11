import java.applet.*;
import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.behaviors.keyboard.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.*;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;

public class HumanRobot extends Applet implements KeyListener {

    private SimpleUniverse universe = null;
    private Canvas3D canvas = null;
    private TransformGroup viewtrans = null;

    private TransformGroup tg = null;
    private Transform3D t3d = null;
    private Transform3D t3dstep = new Transform3D();

    private Matrix4d matrix = new Matrix4d();

    private Switch selector = new Switch(Switch.CHILD_MASK);
    private BitSet flag_i = new BitSet(3);

    private int count = 0;

    public HumanRobot() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse
                .getPreferredConfiguration();

        canvas = new Canvas3D(config);
        add("Center", canvas);
        universe = new SimpleUniverse(canvas);

        canvas.addKeyListener(this);

        BranchGroup scene = createSceneGraph();
        universe.getViewingPlatform().setNominalViewingTransform();

        universe.getViewer().getView().setBackClipDistance(100.0);
        universe.addBranchGraph(scene);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(), 10000.0);

        viewtrans = universe.getViewingPlatform().getViewPlatformTransform();

        KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewtrans);
        keyNavBeh.setSchedulingBounds(bounds);
        PlatformGeometry platformGeom = new PlatformGeometry();
        platformGeom.addChild(keyNavBeh);
        universe.getViewingPlatform().setPlatformGeometry(platformGeom);

        objRoot.addChild(createHumanRobot1());

        return objRoot;
    }

    private BranchGroup createHumanRobot1() {

        BranchGroup objRoot = new BranchGroup();

        tg = new TransformGroup();
        t3d = new Transform3D();

        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        t3d.setTranslation(new Vector3d(0.0, 0.0, -15.0));
        t3d.setRotation(new AxisAngle4f(0.0f, 0.0f, 0.0f, 0.0f));
        t3d.setScale(1.0);

        tg.setTransform(t3d);

        tg.addChild(createHumanRobot2());
        objRoot.addChild(createLight());
        objRoot.addChild(tg);

        objRoot.compile();

        return objRoot;

    }

    private BranchGroup createHumanRobot2() {

        BranchGroup objRoot = new BranchGroup();

        selector.setCapability(Switch.ALLOW_SWITCH_WRITE);

        flag_i.clear(0);
        flag_i.set(2);
        selector.setChildMask(flag_i);

        selector.addChild(createHumanRobot3(0.5, -0.5, -2.08, -2.08, -0.55,
                0.45, -0.78f, 0.78f, 0.4f, -0.4f));
        selector.addChild(createHumanRobot3(-0.5, 0.5, -2.08, -2.08, 0.45,
                -0.55, 0.78f, -0.78f, -0.4f, 0.4f));
        selector.addChild(createHumanRobot3(0.0, 0.0, -1.78, -1.78, -0.05,
                -0.05, 0.0f, 0.0f, 0.0f, 0.0f));

        objRoot.addChild(selector);

        // objRoot.addChild(createLight());

        objRoot.compile();

        return objRoot;

    }

    private BranchGroup createHumanRobot3(double rarm_z, double larm_z,
                                          double rleg_y, double lleg_y, double rleg_z, double lleg_z,
                                          float rarm_r, float larm_r, float rleg_r, float lleg_r) {

        BranchGroup objRoot = new BranchGroup();

        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();

        t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
        tg.setTransform(t3d);

        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        TransformGroup tg_sp_h = new TransformGroup();
        Transform3D t3d_sp_h = new Transform3D();

        t3d_sp_h.setTranslation(new Vector3d(0.0, 1.3, 0.0));
        tg_sp_h.setTransform(t3d_sp_h);

        Appearance ap_sp_h = createAppearance(new Color3f(0.6f, 0.45f, 0.4f));
        tg_sp_h.addChild(new Sphere(0.8f, ap_sp_h));

        TransformGroup tg_sp_re = new TransformGroup();
        Transform3D t3d_sp_re = new Transform3D();

        t3d_sp_re.setTranslation(new Vector3d(-0.2, 1.4, 0.7));
        tg_sp_re.setTransform(t3d_sp_re);

        Appearance ap_sp_re = createAppearance(new Color3f(0.0f, 0.0f, 0.0f));
        tg_sp_re.addChild(new Sphere(0.08f, ap_sp_re));

        TransformGroup tg_sp_le = new TransformGroup();
        Transform3D t3d_sp_le = new Transform3D();

        t3d_sp_le.setTranslation(new Vector3d(0.2, 1.4, 0.7));
        tg_sp_le.setTransform(t3d_sp_le);

        Appearance ap_sp_le = createAppearance(new Color3f(0.0f, 0.0f, 0.0f));
        tg_sp_le.addChild(new Sphere(0.08f, ap_sp_le));

        TransformGroup tg_sp_rant = new TransformGroup();
        Transform3D t3d_sp_rant = new Transform3D();

        t3d_sp_rant.setTranslation(new Vector3d(-1.1, 2.6, 0.0));
        tg_sp_rant.setTransform(t3d_sp_rant);

        Appearance ap_sp_rant = createAppearance(new Color3f(1.0f, 1.0f, 0.0f));
        tg_sp_rant.addChild(new Sphere(0.3f, ap_sp_rant));

        TransformGroup tg_sp_lant = new TransformGroup();
        Transform3D t3d_sp_lant = new Transform3D();

        t3d_sp_lant.setTranslation(new Vector3d(1.1, 2.6, 0.0));
        tg_sp_lant.setTransform(t3d_sp_lant);

        Appearance ap_sp_lant = createAppearance(new Color3f(1.0f, 1.0f, 0.0f));
        tg_sp_lant.addChild(new Sphere(0.3f, ap_sp_lant));

        TransformGroup tg_cy_mo = new TransformGroup();
        Transform3D t3d_cy_mo = new Transform3D();

        t3d_cy_mo.setTranslation(new Vector3d(0.0, 0.95, 0.705));
        t3d_cy_mo.setRotation(new AxisAngle4f(0.0f, 0.0f, 1.0f, 1.57f));
        tg_cy_mo.setTransform(t3d_cy_mo);

        Appearance ap_cy_mo = createAppearance(new Color3f(1.0f, 0.0f, 0.0f));
        tg_cy_mo.addChild(new Cylinder(0.015f, 0.1f, ap_cy_mo));

        TransformGroup tg_cy_rant = new TransformGroup();
        Transform3D t3d_cy_rant = new Transform3D();

        t3d_cy_rant.setTranslation(new Vector3d(-0.86, 2.1, 0.0));
        t3d_cy_rant.setRotation(new AxisAngle4f(0.0f, 0.0f, 1.0f, 0.5f));
        tg_cy_rant.setTransform(t3d_cy_rant);

        Appearance ap_cy_rant = createAppearance(new Color3f(1.0f, 1.0f, 0.0f));
        tg_cy_rant.addChild(new Cylinder(0.05f, 1.0f, ap_cy_rant));

        TransformGroup tg_cy_lant = new TransformGroup();
        Transform3D t3d_cy_lant = new Transform3D();

        t3d_cy_lant.setTranslation(new Vector3d(0.8, 2.1, 0.0));
        t3d_cy_lant.setRotation(new AxisAngle4f(0.0f, 0.0f, 1.0f, -0.5f));
        tg_cy_lant.setTransform(t3d_cy_lant);

        Appearance ap_cy_lant = createAppearance(new Color3f(1.0f, 1.0f, 0.0f));
        tg_cy_lant.addChild(new Cylinder(0.05f, 1.0f, ap_cy_lant));

        TransformGroup tg_cy_t = new TransformGroup();
        Transform3D t3d_cy_t = new Transform3D();

        t3d_cy_t.setTranslation(new Vector3d(0.0, -0.4, 0.0));
        tg_cy_t.setTransform(t3d_cy_t);

        Appearance ap_cy_t = createAppearance(new Color3f(0.0f, 1.0f, 0.0f));
        tg_cy_t.addChild(new Cylinder(0.4f, 1.8f, ap_cy_t));

        TransformGroup tg_cy_rarm = new TransformGroup();
        Transform3D t3d_cy_rarm = new Transform3D();

        t3d_cy_rarm.setTranslation(new Vector3d(-0.6, -0.5, rarm_z));
        t3d_cy_rarm.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, rarm_r));
        tg_cy_rarm.setTransform(t3d_cy_rarm);

        Appearance ap_cy_rarm = createAppearance(new Color3f(0.0f, 1.0f, 0.0f));
        tg_cy_rarm.addChild(new Cylinder(0.08f, 1.5f, ap_cy_rarm));

        TransformGroup tg_cy_larm = new TransformGroup();
        Transform3D t3d_cy_larm = new Transform3D();

        t3d_cy_larm.setTranslation(new Vector3d(0.6, -0.5, larm_z));
        t3d_cy_larm.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, larm_r));
        tg_cy_larm.setTransform(t3d_cy_larm);

        Appearance ap_cy_larm = createAppearance(new Color3f(0.0f, 1.0f, 0.0f));
        tg_cy_larm.addChild(new Cylinder(0.08f, 1.5f, ap_cy_larm));

        TransformGroup tg_cy_rleg = new TransformGroup();
        Transform3D t3d_cy_rleg = new Transform3D();

        t3d_cy_rleg.setTranslation(new Vector3d(-0.2, rleg_y, rleg_z));
        t3d_cy_rleg.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, rleg_r));
        tg_cy_rleg.setTransform(t3d_cy_rleg);

        Appearance ap_cy_rleg = createAppearance(new Color3f(0.0f, 1.0f, 0.0f));
        tg_cy_rleg.addChild(new Cylinder(0.1f, 1.8f, ap_cy_rleg));

        TransformGroup tg_cy_lleg = new TransformGroup();
        Transform3D t3d_cy_lleg = new Transform3D();

        t3d_cy_lleg.setTranslation(new Vector3d(0.2, lleg_y, lleg_z));
        t3d_cy_lleg.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, lleg_r));
        tg_cy_lleg.setTransform(t3d_cy_lleg);

        Appearance ap_cy_lleg = createAppearance(new Color3f(0.0f, 1.0f, 0.0f));
        tg_cy_lleg.addChild(new Cylinder(0.1f, 1.8f, ap_cy_lleg));

        TransformGroup tg_sp_b = new TransformGroup();
        Transform3D t3d_sp_b = new Transform3D();

        t3d_sp_b.setTranslation(new Vector3d(0.0, -0.7, 0.0));
        tg_sp_b.setTransform(t3d_sp_b);

        Appearance ap_sp_b = createAppearance(new Color3f(1.0f, 1.0f, 1.0f));
        tg_sp_b.addChild(new Sphere(0.45f, ap_sp_b));

        tg.addChild(tg_cy_t);
        tg.addChild(tg_cy_rarm);
        tg.addChild(tg_cy_larm);
        tg.addChild(tg_cy_rleg);
        tg.addChild(tg_cy_lleg);
        tg.addChild(tg_sp_b);
        tg.addChild(tg_sp_h);
        tg.addChild(tg_sp_re);
        tg.addChild(tg_sp_le);
        tg.addChild(tg_cy_mo);
        tg.addChild(tg_cy_rant);
        tg.addChild(tg_cy_lant);
        tg.addChild(tg_sp_rant);
        tg.addChild(tg_sp_lant);

        objRoot.addChild(tg);

        // objRoot.addChild(createLight());

        objRoot.compile();

        return objRoot;

    }

    private Appearance createAppearance(Color3f col) {
        Appearance ap = new Appearance();
        Material ma = new Material();
        ma.setDiffuseColor(col);
        ma.setEmissiveColor(col);
        ap.setMaterial(ma);
        return ap;
    }

    private Light createLight() {
        DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f,
                1.0f, 1.0f), new Vector3f(-0.3f, 0.2f, -1.0f));

        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

        return light;
    }

    public static void main(String[] args) {
        HumanRobot applet = new HumanRobot();
        Frame frame = new MainFrame(applet, 800, 600);
    }

    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'w') {
            flag_i.clear();
            flag_i.set(count % 2);
            selector.setChildMask(flag_i);

            t3dstep.set(new Vector3d(0.0, 0.0, 0.5));
            tg.getTransform(t3d);
            t3d.mul(t3dstep);
            tg.setTransform(t3d);

            count++;
        }

        if (key == 's') {
            flag_i.clear();
            flag_i.set(count % 2);
            selector.setChildMask(flag_i);

            t3dstep.set(new Vector3d(0.0, 0.0, -0.5));
            tg.getTransform(t3d);
            t3d.mul(t3dstep);
            tg.setTransform(t3d);

            count++;
        }

        if (key == 'd') {

            flag_i.clear();
            flag_i.set(count % 2);
            selector.setChildMask(flag_i);

            t3dstep.set(new Vector3d(0.0, 0.0, 0.25));
            tg.getTransform(t3d);
            t3d.mul(t3dstep);
            tg.setTransform(t3d);

            t3dstep.rotY(Math.PI / 32);
            tg.getTransform(t3d);
            t3d.get(matrix);
            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.mul(t3dstep);
            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tg.setTransform(t3d);

            count++;

        }

        if (key == 'a') {

            flag_i.clear();
            flag_i.set(count % 2);
            selector.setChildMask(flag_i);

            t3dstep.set(new Vector3d(0.0, 0.0, 0.25));
            tg.getTransform(t3d);
            t3d.mul(t3dstep);
            tg.setTransform(t3d);

            t3dstep.rotY(-Math.PI / 32);
            tg.getTransform(t3d);
            t3d.get(matrix);
            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.mul(t3dstep);
            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tg.setTransform(t3d);

            count++;

        }

        if (key == 'q') {
            t3dstep.rotY(-Math.PI / 32);
            tg.getTransform(t3d);
            t3d.get(matrix);
            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.mul(t3dstep);
            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tg.setTransform(t3d);
        }

        if (key == 'e') {
            t3dstep.rotY(Math.PI / 32);
            tg.getTransform(t3d);
            t3d.get(matrix);
            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.mul(t3dstep);
            t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tg.setTransform(t3d);
        }

        if (key == 'x') {
            flag_i.clear();
            flag_i.set(2);
            selector.setChildMask(flag_i);
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

}