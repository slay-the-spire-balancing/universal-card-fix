{
  inputs = {
    nixpkgs.url = "nixpkgs/nixpkgs-unstable";
  };

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
      jdk = pkgs.jdk8;
    in
    {
      devShell.${system} = pkgs.mkShell {
        name = "java-shell";
        buildInputs = [
          jdk
          pkgs.gradle
        ];

        shellHook = ''
          export JAVA_HOME=${jdk}
          PATH="${jdk}/bin:$PATH"
        '';
      };
    };
}
