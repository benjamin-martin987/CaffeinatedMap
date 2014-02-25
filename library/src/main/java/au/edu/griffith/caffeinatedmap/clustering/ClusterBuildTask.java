package au.edu.griffith.caffeinatedmap.clustering;

import android.os.AsyncTask;

import com.google.android.gms.maps.Projection;

import java.util.ArrayList;
import java.util.List;

import au.edu.griffith.caffeinatedmap.clustering.ClusterBuildTask.BuildTaskArgs;

public class ClusterBuildTask extends AsyncTask<BuildTaskArgs, Void, List<Cluster>> {

    public static class BuildTaskArgs {
        public ClusteringSettings settings;
        public Projection projection;
        public List<Clusterable> clusterables;
    }

    private BuildTaskCallback mCallback;

    public ClusterBuildTask(BuildTaskCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Cluster> doInBackground(BuildTaskArgs... args) {
        List<Cluster> clusters = new ArrayList<Cluster>();

        if (args != null && args.length > 0) {
            BuildTaskArgs buildTaskArgs = args[0];
            if (buildTaskArgs.clusterables != null && buildTaskArgs.projection != null) {
                if (buildTaskArgs.settings == null) {
                    buildTaskArgs.settings = new ClusteringSettings().setClusterOptions(new ClusterOptions());
                }
                for (Clusterable clusterable : buildTaskArgs.clusterables) {
                    clusterable.clearScreenPosition();
                    clusterable.setScreenPosition(buildTaskArgs.projection);
                    boolean addedToCluster = false;

                    for (Cluster cluster : clusters) {
                        if (cluster.getPixelDistanceFrom(clusterable) < buildTaskArgs.settings.getClusterSize()) {
                            cluster.add(clusterable);
                            addedToCluster = true;
                            break;
                        }
                    }

                    if (!addedToCluster) {
                        clusters.add(new Cluster(buildTaskArgs.settings.getClusterOptions(), clusterable, buildTaskArgs.projection));
                    }
                }
            }
        }

        return clusters;
    }

    @Override
    protected void onPostExecute(List<Cluster> clusters) {
        super.onPostExecute(clusters);
        for (Cluster cluster : clusters) {
            cluster.updateClusterPosition();
        }
        mCallback.onBuildTaskReturn(clusters);
    }

    public static interface BuildTaskCallback {
        void onBuildTaskReturn(List<Cluster> clusters);
    }
}
