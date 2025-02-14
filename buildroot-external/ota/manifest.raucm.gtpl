[update]
compatible={{ env "ota_compatible" }}
version={{ env "ota_version" }}

[hooks]
filename=hook
hooks=install-check;

[image.boot]
filename=boot.vfat
hooks=install;

[image.kernel]
filename=kernel.img
{{- $bootloader := (env "BOOTLOADER") }}
{{- if or (eq $bootloader "grub") (eq $bootloader "tryboot") }}
hooks=post-install;
{{- end }}

[image.rootfs]
filename=rootfs.img

{{- if eq (env "BOOT_SPL") "true" }}
[image.spl]
filename=spl.img
hooks=install
{{- end }}
