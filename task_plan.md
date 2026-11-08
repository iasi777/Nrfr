# Nrfr Android 16 Diagnosis

## Goal
Determine why TikTok still cannot connect after the patched Nrfr reports a successful carrier-country override.

## Phases
- [complete] Identify device, Android version, patch level, and installed components.
- [complete] Inspect upstream failure mode and the available source patch.
- [complete] Establish that the patched version previously reported a successful override.
- [complete] Check TikTok package/version, effective country signals, and network reachability.
- [complete] Reinstall the patched Nrfr build and capture a controlled override reproduction.
- [complete] Patch Android 16 handling to use a non-persistent CarrierConfig override and build an APK.
- [complete] Install the patched APK and verify CarrierConfig/TikTok behavior.
- [complete] Commit the source fix and push it to the user's GitHub fork.
- [pending] Record the conclusion and any remaining device-specific limitation.

## Constraints
- Do not collect or expose IMEI, phone number, ICCID, or other subscriber identifiers.
- Do not reset or overwrite carrier configuration without a controlled test.
- Do not install another APK unless diagnostics show the override itself is failing.

## Errors Encountered
| Error | Resolution |
|---|---|
| ADB daemon could not bind inside the sandbox | Started and used ADB with approved elevated execution |
| Nrfr package was not present on the phone | Prepare and install the patched APK |
| Local JDK and Android SDK were not on PATH | Check for existing toolchains, then install only required components if absent |
| Current phone state had no Nrfr package or persistent CarrierConfig override | Reinstall the patched build and reproduce from a known baseline |
| HyperOS Android 16 rejects `persistent=true` for a shell-delegated non-system app | Use `persistent=false`; the override will need reapplying after reboot |
| Source build cannot resolve Android hidden APIs because the fork omits its compile stubs | Apply the same two-boolean change to the already compiled fixed APK, then rebuild and sign |
