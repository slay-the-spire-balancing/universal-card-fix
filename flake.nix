{
  inputs = {
    nixpkgs.url = "nixpkgs/nixpkgs-unstable";
  };

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      devShell.${system} = pkgs.mkShell {
        name = "java-shell";
        buildInputs = [
          pkgs.jdk11
          pkgs.gradle
        ];

        shellHook = ''
          export JAVA_HOME=${pkgs.jdk11}
          PATH="${pkgs.jdk11}/bin:$PATH"
        '';
      };
    };
}
