mkdir /projects/bin
cd /projects/bin
curl -O https://downloads-openshift-console.apps.cluster-85a6.85a6.sandbox661.opentlc.com/amd64/linux/oc.tar
tar -xvf oc.tar
chmod +x oc
export PATH=$PATH:/projects/bin
cd /projects/fuse-workshop