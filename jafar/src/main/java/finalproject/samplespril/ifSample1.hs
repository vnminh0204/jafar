import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 1) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Equal regA regB regA 
    , Push regA 
    , Pop regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 26) 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Jump (Abs 31) 
    , Nop 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , Nop 
    , EndProg 
    ]

main = run [prog0]

